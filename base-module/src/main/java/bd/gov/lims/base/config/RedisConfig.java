package bd.gov.lims.base.config;

import bd.gov.lims.base.util.TokenExpireTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@PropertySource("classpath:redis-${spring.profiles.active}.properties")
@EnableRedisRepositories(shadowCopy = RedisKeyValueAdapter.ShadowCopy.OFF,
        basePackages = {"bd.gov.lims.base.repository"})
public class RedisConfig {
    @Value("${redis.username}")
    private String redisUsername;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.url}")
    private String redisUrl;
    @Value("${redis.port}")
    private Integer redisPort;
    @Value("${redis.database}")
    private Integer redisDatabase;
    @Value("${token.expire.minutes}")
    private Integer tokenExpireInMinutes;
    @Value("${refreshtoken.expire.days}")
    private Integer refreshTokenExpireInDays;
    @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig  jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(128);
        jedisPoolConfig.setMaxIdle(128);
        jedisPoolConfig.setMinIdle(16);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        jedisPoolConfig.setBlockWhenExhausted(true);
        return jedisPoolConfig;
    }
    @Bean()
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        jpcb.poolConfig(jedisPoolConfig());
        jpcb.and().connectTimeout(Duration.ofSeconds(5));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(getRedisConnection(),jpcb.build());
        return jedisConnectionFactory;
    }
    @Bean()
    RedisTemplate<String, Object> redisTemplate() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableTransactionSupport(false);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    RedisStandaloneConfiguration getRedisConnection() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(this.redisDatabase);
        redisStandaloneConfiguration.setHostName(this.redisUrl);
        redisStandaloneConfiguration.setPort(this.redisPort);
        redisStandaloneConfiguration.setUsername(this.redisUsername);
        redisStandaloneConfiguration.setPassword(this.redisPassword);
        return redisStandaloneConfiguration;
    }
    @Bean
    TokenExpireTime getTokenExpireTime() {
        return TokenExpireTime.builder()
                .tokenExpireTimeInMinutes(tokenExpireInMinutes)
                .refreshTokenExpireTimeInDays(refreshTokenExpireInDays)
                .build();
    }

}

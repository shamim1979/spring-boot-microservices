package bd.gov.lims.common.config;

import bd.gov.lims.common.rsa.RsaKeyProvider;
import bd.gov.lims.common.util.DateTimeUtil;
import bd.gov.lims.common.util.ResourceUtil;
import bd.gov.lims.common.util.TokenExpireTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.AntPathMatcher;

@Configuration
public class UtilityConfig {
    @Bean
    DateTimeUtil dateTimeUtil(TokenExpireTime tokenExpireTime) {
        return new DateTimeUtil(tokenExpireTime);
    }
    @Bean
    AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
    @Bean
    ResourceUtil resourceUtil(ResourceLoader resourceLoader) {
        return new ResourceUtil(resourceLoader);
    }
    @Bean
    RsaKeyProvider rsaKeyProvider(ResourceUtil resourceUtil) {
        return new RsaKeyProvider(resourceUtil);
    }

}

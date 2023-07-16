package bd.gov.lims.base.config;

import bd.gov.lims.base.rsa.RsaKeyProvider;
import bd.gov.lims.base.util.DateTimeUtil;
import bd.gov.lims.base.util.ResourceUtil;
import bd.gov.lims.base.util.TokenExpireTime;
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

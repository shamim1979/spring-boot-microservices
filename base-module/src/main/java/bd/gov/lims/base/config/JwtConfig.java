package bd.gov.lims.base.config;

import bd.gov.lims.base.rsa.RsaKeyProvider;
import bd.gov.lims.base.service.JwtService;
import bd.gov.lims.base.util.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    JwtService jwtService(DateTimeUtil dateTimeUtil, RsaKeyProvider rsaKeyProvider) {
        return new JwtService(dateTimeUtil,rsaKeyProvider);
    }
}

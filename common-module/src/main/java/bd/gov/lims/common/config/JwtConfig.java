package bd.gov.lims.common.config;

import bd.gov.lims.common.rsa.RsaKeyProvider;
import bd.gov.lims.common.service.JwtService;
import bd.gov.lims.common.util.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    JwtService jwtService(DateTimeUtil dateTimeUtil, RsaKeyProvider rsaKeyProvider) {
        return new JwtService(dateTimeUtil,rsaKeyProvider);
    }
}

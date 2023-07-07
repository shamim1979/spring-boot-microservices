package bd.gov.lims.common.errorhandling;

import bd.gov.lims.common.errorhandling.handler.SpringSecurityApiExceptionHandler;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDeniedException;

@Configuration
//@ConditionalOnClass(AccessDeniedException.class)
public class SpringSecurityErrorHandlingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SpringSecurityApiExceptionHandler springSecurityApiExceptionHandler(ErrorHandlingProperties properties,
                                                                               HttpStatusMapper httpStatusMapper,
                                                                               ErrorCodeMapper errorCodeMapper,
                                                                               ErrorMessageMapper errorMessageMapper) {
        return new SpringSecurityApiExceptionHandler(properties, httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }
}

package bd.gov.lims.common.errorhandling;

import bd.gov.lims.common.errorhandling.handler.ConstraintViolationApiExceptionHandler;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ConstraintViolationException.class)
public class ValidationErrorHandlingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ConstraintViolationApiExceptionHandler constraintViolationApiExceptionHandler(ErrorHandlingProperties properties,
                                                                                         HttpStatusMapper httpStatusMapper,
                                                                                         ErrorCodeMapper errorCodeMapper,
                                                                                         ErrorMessageMapper errorMessageMapper) {
        return new ConstraintViolationApiExceptionHandler(properties, httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

}

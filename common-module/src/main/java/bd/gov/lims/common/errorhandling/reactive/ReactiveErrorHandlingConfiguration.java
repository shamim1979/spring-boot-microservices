package bd.gov.lims.common.errorhandling.reactive;

import bd.gov.lims.common.errorhandling.*;
import bd.gov.lims.common.errorhandling.handler.*;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.List;
import java.util.stream.Collectors;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties({ErrorHandlingProperties.class, WebProperties.class, ServerProperties.class})
@ConditionalOnProperty(value = "error.handling.enabled", matchIfMissing = true)
@PropertySource("classpath:/error-handling-defaults.properties")
@Import({ValidationErrorHandlingConfiguration.class,
        SpringSecurityErrorHandlingConfiguration.class,
        SpringOrmErrorHandlingConfiguration.class})
public class ReactiveErrorHandlingConfiguration extends AbstractErrorHandlingConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ServerWebInputExceptionHandler serverWebInputExceptionHandler(HttpStatusMapper httpStatusMapper,
                                                                         ErrorCodeMapper errorCodeMapper,
                                                                         ErrorMessageMapper errorMessageMapper) {
        return new ServerWebInputExceptionHandler(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ServerErrorExceptionHandler serverErrorExceptionHandler(HttpStatusMapper httpStatusMapper,
                                                                   ErrorCodeMapper errorCodeMapper,
                                                                   ErrorMessageMapper errorMessageMapper) {
        return new ServerErrorExceptionHandler(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataIntegrityViolationExceptionHandler dataIntegrityViolationExceptionHandler(ErrorHandlingProperties properties,
                                                                                         HttpStatusMapper httpStatusMapper,
                                                                                         ErrorCodeMapper errorCodeMapper,
                                                                                         ErrorMessageMapper errorMessageMapper) {
        return new DataIntegrityViolationExceptionHandler(properties, httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ClientResponseExceptionHandler clientResponseExceptionHandler(HttpStatusMapper httpStatusMapper,
                                                                      ErrorCodeMapper errorCodeMapper,
                                                                      ErrorMessageMapper errorMessageMapper) {
        return new ClientResponseExceptionHandler(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(-2)
    public GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                                         ServerProperties serverProperties,
                                                                         WebProperties webProperties,ObjectProvider<ViewResolver> viewResolvers,
                                                                         ServerCodecConfigurer serverCodecConfigurer,
                                                                         ApplicationContext applicationContext,
                                                                         LoggingService loggingService,
                                                                         List<ApiExceptionHandler> handlers,
                                                                         FallbackApiExceptionHandler fallbackApiExceptionHandler,
                                                                         List<ApiErrorResponseCustomizer> responseCustomizers) {

        GlobalErrorWebExceptionHandler exceptionHandler = new GlobalErrorWebExceptionHandler(errorAttributes,
                                                                                             webProperties.getResources(),
                                                                                             serverProperties.getError(),
                                                                                             applicationContext,
                                                                                             handlers,
                                                                                             fallbackApiExceptionHandler,
                                                                                             loggingService,
                                                                                             responseCustomizers);
        exceptionHandler.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }
}

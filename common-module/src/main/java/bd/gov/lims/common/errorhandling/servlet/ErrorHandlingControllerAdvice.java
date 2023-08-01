package bd.gov.lims.common.errorhandling.servlet;

import bd.gov.lims.common.errorhandling.ApiErrorResponseCustomizer;
import bd.gov.lims.common.errorhandling.ApiExceptionHandler;
import bd.gov.lims.common.errorhandling.FallbackApiExceptionHandler;
import bd.gov.lims.common.errorhandling.LoggingService;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;

@ControllerAdvice(annotations = RestController.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ErrorHandlingControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    private final List<ApiExceptionHandler> handlers;
    private final FallbackApiExceptionHandler fallbackHandler;
    private final LoggingService loggingService;
    private final List<ApiErrorResponseCustomizer> responseCustomizers;

    public ErrorHandlingControllerAdvice(List<ApiExceptionHandler> handlers,
                                         FallbackApiExceptionHandler fallbackHandler,
                                         LoggingService loggingService,
                                         List<ApiErrorResponseCustomizer> responseCustomizers) {
        this.handlers = handlers;
        this.fallbackHandler = fallbackHandler;
        this.loggingService = loggingService;
        this.responseCustomizers = responseCustomizers;
        this.handlers.sort(AnnotationAwareOrderComparator.INSTANCE);

        LOGGER.info("Error Handling Spring Boot Starter active with {} handlers", this.handlers.size());
        LOGGER.debug("Handlers: {}", this.handlers);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Throwable exception, WebRequest webRequest, Locale locale) {
        LOGGER.debug("webRequest: {}", webRequest);
        LOGGER.debug("locale: {}", locale);

        ApiErrorResponseDto errorResponse = null;
        for (ApiExceptionHandler handler : handlers) {
            if (handler.canHandle(exception)) {
                errorResponse = handler.handle(exception);
                break;
            }
        }

        if (errorResponse == null) {
            errorResponse = fallbackHandler.handle(exception);
        }

        for (ApiErrorResponseCustomizer responseCustomizer : responseCustomizers) {
            responseCustomizer.customize(errorResponse);
        }

        loggingService.logException(errorResponse, exception);

        return ResponseEntity.status(errorResponse.getStatus())
                             .body(errorResponse);
    }
}

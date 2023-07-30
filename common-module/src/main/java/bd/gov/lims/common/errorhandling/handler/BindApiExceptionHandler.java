package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import bd.gov.lims.common.support.ApiFieldError;
import bd.gov.lims.common.support.ApiGlobalError;
import bd.gov.lims.common.support.ErrorDto;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;


/**
 * Class to handle {@link BindException} and {@link MethodArgumentNotValidException} exceptions. This is typically
 * used:
 * * when `@Valid` is used on {@link org.springframework.web.bind.annotation.RestController} method arguments.
 * * when `@Valid` is used on {@link org.springframework.web.bind.annotation.RestController} query parameters
 */
public class BindApiExceptionHandler extends AbstractApiExceptionHandler {

    private final ErrorHandlingProperties properties;

    public BindApiExceptionHandler(ErrorHandlingProperties properties,
                                   HttpStatusMapper httpStatusMapper,
                                   ErrorCodeMapper errorCodeMapper,
                                   ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
        this.properties = properties;
    }

    @Override
    public boolean canHandle(Throwable exception) {
        // BindingResult is a common interface between org.springframework.validation.BindException
        // and org.springframework.web.bind.support.WebExchangeBindException
        return exception instanceof BindingResult;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {

        BindingResult bindingResult = (BindingResult) exception;

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(getHttpStatus(exception, HttpStatus.BAD_REQUEST))
                .message(getMessage(bindingResult))
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(getMessage(bindingResult))
                        .build())
                .build();

        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().stream()
                    .map(fieldError -> ApiFieldError.builder()
                            .code(getCode(fieldError))
                            .property(fieldError.getField())
                            .message(getMessage(fieldError))
                            .rejectedValue(fieldError.getRejectedValue())
                            .path(getPath(fieldError))
                            .build())
                    .forEach(error -> response.getError().getFieldErrors().add(error));
        }

        if (bindingResult.hasGlobalErrors()) {
            bindingResult.getGlobalErrors().stream()
                    .map(globalError -> ApiGlobalError.builder()
                            .code(errorCodeMapper.getErrorCode(globalError.getCode()))
                            .message(errorMessageMapper.getErrorMessage(globalError.getCode(), globalError.getDefaultMessage()))
                            .build())
                    .forEach(error -> response.getError().getGlobalErrors().add(error));
        }

        return response;
    }

    private String getCode(FieldError fieldError) {
        String code = fieldError.getCode();
        String fieldSpecificCode = fieldError.getField() + "." + code;
        return errorCodeMapper.getErrorCode(fieldSpecificCode, code);
    }

    private String getMessage(FieldError fieldError) {
        String code = fieldError.getCode();
        String fieldSpecificCode = fieldError.getField() + "." + code;
        return errorMessageMapper.getErrorMessage(fieldSpecificCode, code, fieldError.getDefaultMessage());
    }

    private String getMessage(BindingResult bindingResult) {
        return "Validation failed for object='" + bindingResult.getObjectName() + "'. Error count: " + bindingResult.getErrorCount();
    }

    private String getPath(FieldError fieldError) {
        if (!properties.isAddPathToError()) {
            return null;
        }

        String path = null;
        try {
            path = fieldError.unwrap(ConstraintViolationImpl.class)
                             .getPropertyPath()
                             .toString();
        } catch (RuntimeException runtimeException) {
            // only set a path if we have a ConstraintViolation
        }
        return path;
    }
}

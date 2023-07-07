package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ApiErrorResponse;
import bd.gov.lims.common.errorhandling.ApiFieldError;
import bd.gov.lims.common.errorhandling.ApiGlobalError;
import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


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
    public ApiErrorResponse handle(Throwable exception) {

        BindingResult bindingResult = (BindingResult) exception;
        ApiErrorResponse response = new ApiErrorResponse(getHttpStatus(exception, HttpStatus.BAD_REQUEST),
                                                         getErrorCode(exception),
                                                         getMessage(bindingResult));
        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().stream()
                         .map(fieldError -> new ApiFieldError(getCode(fieldError),
                                                              fieldError.getField(),
                                                              getMessage(fieldError),
                                                              fieldError.getRejectedValue(),
                                                              getPath(fieldError)))
                         .forEach(response::addFieldError);
        }

        if (bindingResult.hasGlobalErrors()) {
            bindingResult.getGlobalErrors().stream()
                         .map(globalError -> new ApiGlobalError(errorCodeMapper.getErrorCode(globalError.getCode()),
                                                                errorMessageMapper.getErrorMessage(globalError.getCode(), globalError.getDefaultMessage())))
                         .forEach(response::addGlobalError);
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

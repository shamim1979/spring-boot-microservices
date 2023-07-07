package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ApiErrorResponse;
import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class TypeMismatchApiExceptionHandler extends AbstractApiExceptionHandler {
    public TypeMismatchApiExceptionHandler(ErrorHandlingProperties properties,
                                           HttpStatusMapper httpStatusMapper,
                                           ErrorCodeMapper errorCodeMapper,
                                           ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof TypeMismatchException;
    }

    @Override
    public ApiErrorResponse handle(Throwable exception) {
        ApiErrorResponse response = new ApiErrorResponse(getHttpStatus(exception, HttpStatus.BAD_REQUEST),
                                                         getErrorCode(exception),
                                                         getErrorMessage(exception));
        TypeMismatchException ex = (TypeMismatchException) exception;
        response.addErrorProperty("property", getPropertyName(ex));
        response.addErrorProperty("rejectedValue", ex.getValue());
        response.addErrorProperty("expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getName() : null);
        return response;
    }

    private String getPropertyName(TypeMismatchException exception) {
        if (exception instanceof MethodArgumentTypeMismatchException) {
            return ((MethodArgumentTypeMismatchException) exception).getName();
        } else {
            return exception.getPropertyName();
        }
    }
}

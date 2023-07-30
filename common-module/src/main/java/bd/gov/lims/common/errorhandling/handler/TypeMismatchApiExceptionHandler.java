package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import bd.gov.lims.common.support.ErrorDto;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

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
    public ApiErrorResponseDto handle(Throwable exception) {

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getErrorMessage(exception))
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(getErrorMessage(exception))
                        .build())
                .build();

        TypeMismatchException ex = (TypeMismatchException) exception;
        response.getError().getProperties().put("property", getPropertyName(ex));
        response.getError().getProperties().put("rejectedValue", ex.getValue());
        response.getError().getProperties().put("expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getName() : null);
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

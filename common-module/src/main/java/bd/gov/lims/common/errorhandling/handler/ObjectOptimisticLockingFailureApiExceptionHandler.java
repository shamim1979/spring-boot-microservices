package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import bd.gov.lims.common.support.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.time.Instant;

public class ObjectOptimisticLockingFailureApiExceptionHandler extends AbstractApiExceptionHandler {

    public ObjectOptimisticLockingFailureApiExceptionHandler(ErrorHandlingProperties properties,
                                                             HttpStatusMapper httpStatusMapper,
                                                             ErrorCodeMapper errorCodeMapper,
                                                             ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof ObjectOptimisticLockingFailureException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(getHttpStatus(exception, HttpStatus.CONFLICT))
                .message(exception.getMessage())
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(exception.getMessage())
                        .build())
                .build();

        ObjectOptimisticLockingFailureException ex = (ObjectOptimisticLockingFailureException) exception;
        response.getError().getProperties().put("identifier", ex.getIdentifier());
        response.getError().getProperties().put("persistentClassName", ex.getPersistentClassName());
        return response;
    }
}

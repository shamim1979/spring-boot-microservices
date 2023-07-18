package bd.gov.lims.common.errorhandling.handler;

import bd.gov.lims.base.support.ApiErrorResponseDto;
import bd.gov.lims.base.support.ErrorDto;
import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Slf4j
public class DataIntegrityViolationExceptionHandler extends AbstractApiExceptionHandler {
    public DataIntegrityViolationExceptionHandler(ErrorHandlingProperties properties,
                                                  HttpStatusMapper httpStatusMapper,
                                                  ErrorCodeMapper errorCodeMapper,
                                                  ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof DataIntegrityViolationException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(getHttpStatus(exception, HttpStatus.BAD_REQUEST))
                .message(getCustomMessage(exception))
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(getCustomMessage(exception))
                        .build())
                .build();
        return response;
    }

    private String getCustomMessage(Throwable exception) {
        if (exception.getCause() != null && exception.getCause().getCause() != null) {
            return exception.getCause().getCause().getMessage();
        }
        if (exception.getCause() != null) {
            return exception.getCause().getMessage();
        }
        return exception.getMessage();
    }
}

package bd.gov.lims.common.errorhandling.handler;

import bd.gov.lims.base.support.ApiErrorResponseDto;
import bd.gov.lims.base.support.ErrorDto;
import bd.gov.lims.common.errorhandling.ApiExceptionHandler;
import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.Instant;

/**
 * {@link ApiExceptionHandler} for
 * {@link HttpMessageNotReadableException}. This typically happens when Spring can't properly
 * decode the incoming request to JSON.
 */
public class HttpMessageNotReadableApiExceptionHandler extends AbstractApiExceptionHandler {
    public HttpMessageNotReadableApiExceptionHandler(ErrorHandlingProperties properties,
                                                     HttpStatusMapper httpStatusMapper,
                                                     ErrorCodeMapper errorCodeMapper,
                                                     ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof HttpMessageNotReadableException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        return ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getErrorMessage(exception))
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(getErrorMessage(exception))
                        .build())
                .build();
    }

}

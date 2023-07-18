package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.base.support.ApiErrorResponseDto;
import bd.gov.lims.base.support.ErrorDto;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.time.Instant;

public class ServerWebInputExceptionHandler extends AbstractApiExceptionHandler {
    public ServerWebInputExceptionHandler(HttpStatusMapper httpStatusMapper,
                                          ErrorCodeMapper errorCodeMapper,
                                          ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof ServerWebInputException
                // WebExchangeBindException should be handled by BindApiExceptionHandler
                && !(exception instanceof WebExchangeBindException);
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        ServerWebInputException ex = (ServerWebInputException) exception;

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(ex.getStatusCode().value())
                .message(ex.getMessage())
                .error(ErrorDto.builder()
                        .code(getErrorCode(exception))
                        .message(ex.getMessage())
                        .build())
                .build();

        MethodParameter methodParameter = ex.getMethodParameter();
        if (methodParameter != null) {
            response.getError().getProperties().put("parameterName", methodParameter.getParameterName());
            response.getError().getProperties().put("parameterType", methodParameter.getParameterType().getSimpleName());
        }
        return response;
    }
}

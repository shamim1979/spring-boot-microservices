package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import bd.gov.lims.common.support.ErrorDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.server.ServerErrorException;

import java.lang.reflect.Method;
import java.time.Instant;

public class ServerErrorExceptionHandler extends AbstractApiExceptionHandler {
    public ServerErrorExceptionHandler(HttpStatusMapper httpStatusMapper,
                                       ErrorCodeMapper errorCodeMapper,
                                       ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof ServerErrorException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        ServerErrorException ex = (ServerErrorException) exception;

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

        Method handlerMethod = ex.getHandlerMethod();
        if (handlerMethod != null) {
            response.getError().getProperties().put("methodName", handlerMethod.getName());
            response.getError().getProperties().put("methodClassName", handlerMethod.getDeclaringClass().getSimpleName());
        }

        return response;
    }
}

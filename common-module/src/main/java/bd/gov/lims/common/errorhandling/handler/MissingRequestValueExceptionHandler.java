package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.base.support.ApiErrorResponseDto;
import bd.gov.lims.base.support.ErrorDto;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.*;

import java.time.Instant;

public class MissingRequestValueExceptionHandler extends AbstractApiExceptionHandler {
    public MissingRequestValueExceptionHandler(HttpStatusMapper httpStatusMapper,
                                               ErrorCodeMapper errorCodeMapper,
                                               ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof MissingRequestValueException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getErrorMessage(exception))
                .error(ErrorDto.builder()
                        .message(getErrorMessage(exception))
                        .build())
                .build();

        if (exception instanceof MissingMatrixVariableException) {
            response.getError().getProperties().put("variableName", ((MissingMatrixVariableException) exception).getVariableName());
            addParameterInfo(response, ((MissingMatrixVariableException) exception).getParameter());
        } else if (exception instanceof MissingPathVariableException) {
            response.getError().getProperties().put("variableName", ((MissingPathVariableException) exception).getVariableName());
            addParameterInfo(response, ((MissingPathVariableException) exception).getParameter());
        } else if (exception instanceof MissingRequestCookieException) {
            response.getError().getProperties().put("cookieName", ((MissingRequestCookieException) exception).getCookieName());
            addParameterInfo(response, ((MissingRequestCookieException) exception).getParameter());
        } else if (exception instanceof MissingRequestHeaderException) {
            response.getError().getProperties().put("headerName", ((MissingRequestHeaderException) exception).getHeaderName());
            addParameterInfo(response, ((MissingRequestHeaderException) exception).getParameter());
        } else if (exception instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) exception).getParameterName();
            String parameterType = ((MissingServletRequestParameterException) exception).getParameterType();
            response.getError().getProperties().put("parameterName", parameterName);
            response.getError().getProperties().put("parameterType", parameterType);
        }

        return response;
    }

    private void addParameterInfo(ApiErrorResponseDto response, MethodParameter parameter) {
        response.getError().getProperties().put("parameterName", parameter.getParameterName());
        response.getError().getProperties().put("parameterType", parameter.getParameterType().getSimpleName());
    }

    private HttpStatus getHttpStatus(Throwable exception) {
        if (exception instanceof MissingPathVariableException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.BAD_REQUEST;
    }
}

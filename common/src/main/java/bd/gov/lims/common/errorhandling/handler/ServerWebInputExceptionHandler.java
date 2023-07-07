package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ApiErrorResponse;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

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
    public ApiErrorResponse handle(Throwable exception) {
        ServerWebInputException ex = (ServerWebInputException) exception;
        HttpStatusCode status = ex.getStatusCode();
        ApiErrorResponse response = new ApiErrorResponse(status,
                                                         getErrorCode(exception),
                                                         getErrorMessage(exception));
        MethodParameter methodParameter = ex.getMethodParameter();
        if (methodParameter != null) {
            response.addErrorProperty("parameterName", methodParameter.getParameterName());
            response.addErrorProperty("parameterType", methodParameter.getParameterType().getSimpleName());
        }
        return response;
    }
}

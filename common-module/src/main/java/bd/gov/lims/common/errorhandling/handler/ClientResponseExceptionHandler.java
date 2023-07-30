package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.exception.ClientResponseException;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;

public class ClientResponseExceptionHandler extends AbstractApiExceptionHandler {
    public ClientResponseExceptionHandler(HttpStatusMapper httpStatusMapper,
                                          ErrorCodeMapper errorCodeMapper,
                                          ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return exception instanceof ClientResponseException;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        ClientResponseException ex = (ClientResponseException) exception;
        return ex.getApiErrorResponseDto();
    }
}

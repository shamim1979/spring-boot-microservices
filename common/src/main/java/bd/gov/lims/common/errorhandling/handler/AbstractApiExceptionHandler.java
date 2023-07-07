package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ApiExceptionHandler;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public abstract class AbstractApiExceptionHandler implements ApiExceptionHandler {
    protected final HttpStatusMapper httpStatusMapper;
    protected final ErrorCodeMapper errorCodeMapper;
    protected final ErrorMessageMapper errorMessageMapper;

    public AbstractApiExceptionHandler(HttpStatusMapper httpStatusMapper,
                                       ErrorCodeMapper errorCodeMapper,
                                       ErrorMessageMapper errorMessageMapper) {
        this.httpStatusMapper = httpStatusMapper;
        this.errorCodeMapper = errorCodeMapper;
        this.errorMessageMapper = errorMessageMapper;
    }

    protected HttpStatusCode getHttpStatus(Throwable exception, HttpStatus defaultHttpStatus) {
        return httpStatusMapper.getHttpStatus(exception, defaultHttpStatus);
    }

    protected String getErrorCode(Throwable exception) {
        return errorCodeMapper.getErrorCode(exception);
    }

    protected String getErrorMessage(Throwable exception) {
        return errorMessageMapper.getErrorMessage(exception);
    }
}

package bd.gov.lims.common.errorhandling.handler;


import bd.gov.lims.common.errorhandling.ErrorHandlingProperties;
import bd.gov.lims.common.errorhandling.ApiErrorResponse;
import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

public class SpringSecurityApiExceptionHandler extends AbstractApiExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_TO_STATUS_MAPPING;

    static {
        EXCEPTION_TO_STATUS_MAPPING = new HashMap<>();
        /*EXCEPTION_TO_STATUS_MAPPING.put(AccessDeniedException.class, FORBIDDEN);
        EXCEPTION_TO_STATUS_MAPPING.put(AccountExpiredException.class, BAD_REQUEST);
        EXCEPTION_TO_STATUS_MAPPING.put(AuthenticationCredentialsNotFoundException.class, UNAUTHORIZED);
        EXCEPTION_TO_STATUS_MAPPING.put(AuthenticationServiceException.class, INTERNAL_SERVER_ERROR);
        EXCEPTION_TO_STATUS_MAPPING.put(BadCredentialsException.class, BAD_REQUEST);
        EXCEPTION_TO_STATUS_MAPPING.put(UsernameNotFoundException.class, BAD_REQUEST);
        EXCEPTION_TO_STATUS_MAPPING.put(InsufficientAuthenticationException.class, UNAUTHORIZED);
        EXCEPTION_TO_STATUS_MAPPING.put(LockedException.class, BAD_REQUEST);
        EXCEPTION_TO_STATUS_MAPPING.put(DisabledException.class, BAD_REQUEST);*/
    }

    public SpringSecurityApiExceptionHandler(ErrorHandlingProperties properties,
                                             HttpStatusMapper httpStatusMapper,
                                             ErrorCodeMapper errorCodeMapper,
                                             ErrorMessageMapper errorMessageMapper) {
        super(httpStatusMapper, errorCodeMapper, errorMessageMapper);
    }

    @Override
    public boolean canHandle(Throwable exception) {
        return EXCEPTION_TO_STATUS_MAPPING.containsKey(exception.getClass());
    }

    @Override
    public ApiErrorResponse handle(Throwable exception) {
        HttpStatus httpStatus = EXCEPTION_TO_STATUS_MAPPING.getOrDefault(exception.getClass(), INTERNAL_SERVER_ERROR);
        return new ApiErrorResponse(getHttpStatus(exception, httpStatus),
                                    getErrorCode(exception),
                                    getErrorMessage(exception));
    }
}
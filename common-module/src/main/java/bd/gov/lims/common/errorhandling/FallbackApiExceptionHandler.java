package bd.gov.lims.common.errorhandling;


public interface FallbackApiExceptionHandler {
    ApiErrorResponse handle(Throwable exception);
}

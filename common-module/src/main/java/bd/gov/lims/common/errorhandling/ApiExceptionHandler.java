package bd.gov.lims.common.errorhandling;


import bd.gov.lims.common.support.ApiErrorResponseDto;

public interface ApiExceptionHandler {
    /**
     * Determine if this {@link ApiExceptionHandler} can handle the given {@link Throwable}.
     * It is guaranteed that this method is called first, and the {@link #handle(Throwable)} method
     * will only be called if this method returns <code>true</code>.
     *
     * @param exception the Throwable that needs to be handled
     * @return true if this handler can handle the Throwable, false otherwise.
     */

    boolean canHandle(Throwable exception);

    /**
     * Handle the given {@link Throwable} and return an {@link ApiErrorResponseDto} instance
     * that will be serialized to JSON and returned from the controller method that has
     * thrown the Throwable.
     *
     * @param exception the Throwable that needs to be handled
     * @return the non-null ApiErrorResponse
     */

    ApiErrorResponseDto handle(Throwable exception);
}

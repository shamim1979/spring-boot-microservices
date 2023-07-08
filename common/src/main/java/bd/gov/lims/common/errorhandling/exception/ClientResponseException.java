package bd.gov.lims.common.errorhandling.exception;

import bd.gov.lims.common.errorhandling.ApiErrorResponse;
import lombok.Getter;

@Getter
public class ClientResponseException extends RuntimeException{
    private final ApiErrorResponse apiErrorResponse;
    public ClientResponseException(ApiErrorResponse apiErrorResponse) {
        super(apiErrorResponse.getMessage());
        this.apiErrorResponse = apiErrorResponse;
    }
}

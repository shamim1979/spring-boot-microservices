package bd.gov.lims.common.errorhandling.exception;

import bd.gov.lims.base.support.ApiErrorResponseDto;
import lombok.Getter;

@Getter
public class ClientResponseException extends RuntimeException{
    private final ApiErrorResponseDto apiErrorResponseDto;
    public ClientResponseException(ApiErrorResponseDto apiErrorResponseDto) {
        super(apiErrorResponseDto.getMessage());
        this.apiErrorResponseDto = apiErrorResponseDto;
    }
}

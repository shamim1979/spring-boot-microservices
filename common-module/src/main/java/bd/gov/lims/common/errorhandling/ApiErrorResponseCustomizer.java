package bd.gov.lims.common.errorhandling;


import bd.gov.lims.common.support.ApiErrorResponseDto;

public interface ApiErrorResponseCustomizer {
    void customize(ApiErrorResponseDto response);
}

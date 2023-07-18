package bd.gov.lims.common.errorhandling;


import bd.gov.lims.base.support.ApiErrorResponseDto;

public interface ApiErrorResponseCustomizer {
    void customize(ApiErrorResponseDto response);
}

package bd.gov.lims.common.controller;

import bd.gov.lims.base.support.ApiResponseDto;
import bd.gov.lims.base.support.DeleteResponseDto;
import bd.gov.lims.common.dto.BaseDto;
import bd.gov.lims.common.entity.BaseEntity;
import bd.gov.lims.common.param.BaseParam;
import bd.gov.lims.common.param.PageableParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BaseController<S extends BaseParam, T extends BaseEntity, U extends BaseDto> {
    ResponseEntity<Mono<ApiResponseDto<U>>> findById(UUID id);
    ResponseEntity findAll(Specification<T> specification, PageableParam pageable);
    ResponseEntity<Mono<ApiResponseDto<U>>> save(S param);
    ResponseEntity<Mono<ApiResponseDto<U>>> update(UUID id, S param);
    ResponseEntity<Mono<DeleteResponseDto>> deleteById(UUID id);
}

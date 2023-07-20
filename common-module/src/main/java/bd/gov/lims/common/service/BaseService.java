package bd.gov.lims.common.service;

import bd.gov.lims.common.dto.BaseDto;
import bd.gov.lims.common.entity.BaseEntity;
import bd.gov.lims.common.param.BaseParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<S extends BaseParam, T extends BaseEntity, U extends BaseDto> {
    Page<U> findAll(Specification<T> specification, Pageable pageable);
    List<U> findAll(Specification<T> specification, Sort sort);
    Optional<U> findById(UUID id);
    Optional<U> save(S param);
    Optional<U> update(UUID id, S param);
    void deleteById(UUID id);
}
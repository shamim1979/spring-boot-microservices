package bd.gov.lims.authorization.controller;

import bd.gov.lims.authorization.dto.PrivilegeDto;
import bd.gov.lims.authorization.entity.Privilege;
import bd.gov.lims.authorization.param.PrivilegeParam;
import bd.gov.lims.authorization.service.PrivilegeService;
import bd.gov.lims.common.controller.BaseController;
import bd.gov.lims.common.param.PageableParam;
import bd.gov.lims.common.route.ApiProvider;
import bd.gov.lims.common.service.ApiResponseService;
import bd.gov.lims.common.support.ApiResponseDto;
import bd.gov.lims.common.support.DeleteResponseDto;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(ApiProvider.Privilege.ROOTPATH)
public class PrivilegeController implements BaseController<PrivilegeParam, Privilege, PrivilegeDto> {
    private final PrivilegeService<PrivilegeParam, Privilege, PrivilegeDto> privilegeService;
    PrivilegeController(PrivilegeService<PrivilegeParam, Privilege, PrivilegeDto> privilegeService) {
        this.privilegeService = privilegeService;
    }
    @Override
    @GetMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<PrivilegeDto>>> findById(@PathVariable("id") UUID id) {
        Optional<PrivilegeDto> optionalPrivilegeDto = privilegeService.findById(id);
        return ApiResponseService.generateResponse(optionalPrivilegeDto, HttpStatus.OK, "Privilege is found successfully");
    }
    @Override
    @GetMapping
    public ResponseEntity findAll(@And({
            @Spec(path = "active", params = "active", defaultVal = "true", spec = Equal.class),
            @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class)
    }) Specification<Privilege> specification, PageableParam pageable) {
        if (pageable.isPageable()) {
            Page<PrivilegeDto> privilegeDtoPage = privilegeService.findAll(specification, pageable.getPageable());
            return ApiResponseService.generateResponse(privilegeDtoPage, HttpStatus.OK, "Privilege list is found successfully");
        }
        List<PrivilegeDto> privilegeDtoList = privilegeService.findAll(specification, pageable.getSort());
        return ApiResponseService.generateResponse(privilegeDtoList, HttpStatus.OK, "Privilege list is found successfully");
    }
    @Override
    @PostMapping
    public ResponseEntity<Mono<ApiResponseDto<PrivilegeDto>>> save(@Valid @RequestBody PrivilegeParam param) {
        Optional<PrivilegeDto> optionalPrivilegeDto = privilegeService.save(param);
        return ApiResponseService.generateResponse(optionalPrivilegeDto, HttpStatus.CREATED, "Privilege is created successfully");
    }
    @Override
    @PutMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<PrivilegeDto>>> update(@PathVariable UUID id, @Valid @RequestBody PrivilegeParam param) {
        Optional<PrivilegeDto> optionalPrivilegeDto = privilegeService.update(id, param);
        return ApiResponseService.generateResponse(optionalPrivilegeDto, HttpStatus.OK, "Privilege is updated successfully");
    }
    @Override
    @DeleteMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<DeleteResponseDto>> deleteById(@PathVariable UUID id) {
        privilegeService.deleteById(id);
        return ApiResponseService.generateResponse(HttpStatus.OK, "Privilege is deleted successfully");
    }
}

package bd.gov.lims.user.controller;

import bd.gov.lims.common.controller.BaseController;
import bd.gov.lims.common.param.PageableParam;
import bd.gov.lims.common.route.ApiProvider;
import bd.gov.lims.common.service.ApiResponseService;
import bd.gov.lims.common.support.ApiResponseDto;
import bd.gov.lims.common.support.DeleteResponseDto;
import bd.gov.lims.user.dto.RoleDto;
import bd.gov.lims.user.entity.Role;
import bd.gov.lims.user.param.RoleParam;
import bd.gov.lims.user.service.RoleService;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(ApiProvider.Role.ROOTPATH)
public class RoleController implements BaseController<RoleParam, Role, RoleDto> {
    private final RoleService<RoleParam, Role, RoleDto> roleService;
    @Autowired
    RoleController(RoleService<RoleParam, Role, RoleDto> roleService) {
        this.roleService = roleService;
    }
    @Override
    @GetMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<RoleDto>>> findById(@PathVariable("id") UUID id) {
        Optional<RoleDto> optionalRoleDto = roleService.findById(id);
        return ApiResponseService.generateResponse(optionalRoleDto, HttpStatus.OK, "Role is found successfully");
    }
    @Override
    @GetMapping
    public ResponseEntity findAll(@And({
            @Spec(path = "active", params = "active", defaultVal = "true", spec = Equal.class),
            @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class)
    }) Specification<Role> specification, PageableParam pageable) {
        if (pageable.isPageable()) {
            Page<RoleDto> roleDtoPage = roleService.findAll(specification,pageable.getPageable());
            return ApiResponseService.generateResponse(roleDtoPage, HttpStatus.OK,"Role list is found successfully");
        }
        List<RoleDto> roleDtoList = roleService.findAll(specification,pageable.getSort());
        return ApiResponseService.generateResponse(roleDtoList, HttpStatus.OK,"Role list is found successfully");
    }
    @Override
    @PostMapping
    public ResponseEntity<Mono<ApiResponseDto<RoleDto>>> save(@Valid @RequestBody RoleParam param) {
        Optional<RoleDto> optionalRoleDto = roleService.save(param);
        return ApiResponseService.generateResponse(optionalRoleDto, HttpStatus.CREATED, "Role is created successfully");
    }
    @Override
    @PutMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<RoleDto>>> update(@PathVariable("id") UUID id, @Valid @RequestBody RoleParam param) {
        Optional<RoleDto> optionalRoleDto = roleService.update(id, param);
        return ApiResponseService.generateResponse(optionalRoleDto, HttpStatus.OK, "Role is updated successfully");
    }
    @Override
    @DeleteMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<DeleteResponseDto>> deleteById(@PathVariable("id") UUID id) {
        roleService.deleteById(id);
        return ApiResponseService.generateResponse(HttpStatus.OK, "Role is deleted successfully");
    }
}

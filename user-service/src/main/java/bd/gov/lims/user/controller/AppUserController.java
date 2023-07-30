package bd.gov.lims.user.controller;

import bd.gov.lims.common.controller.BaseController;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.PageableParam;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.common.route.ApiProvider;
import bd.gov.lims.common.service.ApiResponseService;
import bd.gov.lims.common.support.ApiResponseDto;
import bd.gov.lims.common.support.DeleteResponseDto;
import bd.gov.lims.user.entity.AppUser;
import bd.gov.lims.user.service.AppUserService;
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
@RequestMapping(ApiProvider.AppUser.ROOTPATH)
public class AppUserController implements BaseController<UserParam, AppUser, UserDto> {
    private final AppUserService<UserParam, AppUser, UserDto> userService;
    @Autowired
    AppUserController(AppUserService<UserParam, AppUser, UserDto> userService) {
        this.userService = userService;
    }
    @Override
    @GetMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<UserDto>>> findById(@PathVariable("id") UUID id) {
        Optional<UserDto> optionalUserDto = userService.findById(id);
        return ApiResponseService.generateResponse(optionalUserDto,HttpStatus.OK, "User is found successfully");
    }

    @Override
    @GetMapping
    public ResponseEntity findAll(@And({
            @Spec(path = "active", params = "active", defaultVal = "true", spec = Equal.class),
            @Spec(path = "username", params = "username", spec = LikeIgnoreCase.class)
    }) Specification<AppUser> specification, PageableParam pageable) {
        if (pageable.isPageable()) {
            Page<UserDto> userDtoPage = userService.findAll(specification,pageable.getPageable());
            return ApiResponseService.generateResponse(userDtoPage, HttpStatus.OK, "User list is found successfully");
        }
        List<UserDto> userDtoList = userService.findAll(specification,pageable.getSort());
        return ApiResponseService.generateResponse(userDtoList, HttpStatus.OK, "User List is found successfully");
    }

    @Override
    @PostMapping()
    public ResponseEntity<Mono<ApiResponseDto<UserDto>>> save(@Valid @RequestBody UserParam param) {
        Optional<UserDto> optionalUserDto = userService.save(param);
        return ApiResponseService.generateResponse(optionalUserDto, HttpStatus.CREATED, "User is created successfully");
    }

    @Override
    @PutMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<ApiResponseDto<UserDto>>> update(@PathVariable("id") UUID id, @Valid @RequestBody UserParam param) {
        Optional<UserDto> optionalUserDto = userService.update(id, param);
        return ApiResponseService.generateResponse(optionalUserDto, HttpStatus.OK, "User is updated successfully");
    }

    @Override
    @DeleteMapping(ApiProvider.IDENTIFIER)
    public ResponseEntity<Mono<DeleteResponseDto>> deleteById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return ApiResponseService.generateResponse(HttpStatus.OK, "User is deleted successfully");
    }
}

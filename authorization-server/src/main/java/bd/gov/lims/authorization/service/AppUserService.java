package bd.gov.lims.authorization.service;

import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.common.service.BaseService;

import java.util.Optional;

public interface AppUserService<S extends UserParam,T extends AppUser,U extends UserDto> extends BaseService<S, T, U> {
    Optional<T> loadAppUserByUsernameOrEmailOrPhone(String username);
}

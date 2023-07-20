package bd.gov.lims.user.service;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.common.service.BaseService;
import bd.gov.lims.user.entity.AppUser;

public interface UserService<S extends UserParam,T extends AppUser,U extends UserDto> extends BaseService<S, T, U> {
}

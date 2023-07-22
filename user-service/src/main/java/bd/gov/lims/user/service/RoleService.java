package bd.gov.lims.user.service;

import bd.gov.lims.common.service.BaseService;
import bd.gov.lims.user.dto.RoleDto;
import bd.gov.lims.user.entity.Role;
import bd.gov.lims.user.param.RoleParam;

public interface RoleService<S extends RoleParam, T extends Role, U extends RoleDto> extends BaseService<S, T, U> {
}

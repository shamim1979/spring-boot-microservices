package bd.gov.lims.authorization.service;

import bd.gov.lims.authorization.dto.RoleDto;
import bd.gov.lims.authorization.entity.Role;
import bd.gov.lims.authorization.param.RoleParam;
import bd.gov.lims.common.service.BaseService;

public interface RoleService<S extends RoleParam, T extends Role, U extends RoleDto> extends BaseService<S, T, U> {
}

package bd.gov.lims.authorization.mapper;

import bd.gov.lims.authorization.dto.RoleDto;
import bd.gov.lims.authorization.entity.Role;
import bd.gov.lims.authorization.param.RoleParam;
import bd.gov.lims.common.mapper.ConfigMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface RoleMapper {
    RoleDto entityToDto(Role role);
    Role paramToEntity(RoleParam roleParam);
    void paramToEntity(RoleParam roleParam, @MappingTarget Role role);
}

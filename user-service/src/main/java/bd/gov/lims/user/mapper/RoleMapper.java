package bd.gov.lims.user.mapper;

import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.user.dto.RoleDto;
import bd.gov.lims.user.entity.Role;
import bd.gov.lims.user.param.RoleParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface RoleMapper {
    RoleDto entityToDto(Role role);
    Role paramToEntity(RoleParam roleParam);
    void paramToEntity(RoleParam roleParam, @MappingTarget Role role);
}

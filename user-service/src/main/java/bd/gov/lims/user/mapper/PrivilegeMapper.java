package bd.gov.lims.user.mapper;

import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.user.dto.PrivilegeDto;
import bd.gov.lims.user.entity.Privilege;
import bd.gov.lims.user.param.PrivilegeParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface PrivilegeMapper {
    @Mapping(target = "roles", ignore = true)
    PrivilegeDto entityToDto(Privilege privilege);
    Privilege paramToEntity(PrivilegeParam privilegeParam);
    void paramToEntity(PrivilegeParam privilegeParam, @MappingTarget Privilege privilege);
}

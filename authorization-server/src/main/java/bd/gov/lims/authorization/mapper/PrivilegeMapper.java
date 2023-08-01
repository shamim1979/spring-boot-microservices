package bd.gov.lims.authorization.mapper;

import bd.gov.lims.authorization.dto.PrivilegeDto;
import bd.gov.lims.authorization.entity.Privilege;
import bd.gov.lims.authorization.param.PrivilegeParam;
import bd.gov.lims.common.mapper.ConfigMapper;
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

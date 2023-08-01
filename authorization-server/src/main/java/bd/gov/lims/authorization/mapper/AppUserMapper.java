package bd.gov.lims.authorization.mapper;

import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.common.param.UserParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface AppUserMapper {
    UserDto entityToDto(AppUser user);
    AppUser paramToEntity(UserParam userParam);
    void paramToEntity(UserParam userParam, @MappingTarget AppUser appUser);
}

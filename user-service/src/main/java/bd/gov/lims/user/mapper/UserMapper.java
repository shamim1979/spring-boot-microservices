package bd.gov.lims.user.mapper;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.user.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = ConfigMapper.class)
public interface UserMapper {
    UserDto entityToDto(AppUser user);
    AppUser paramToEntity(UserParam userParam);
    void paramToEntity(UserParam userParam, @MappingTarget AppUser appUser);
}

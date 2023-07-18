package bd.gov.lims.user.mapper;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.user.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface UserMapper {
    UserDto userToUserDto(AppUser user);
    AppUser userDtoToUser(UserDto userDto);
    AppUser userParamToUser(UserParam userParam);
}

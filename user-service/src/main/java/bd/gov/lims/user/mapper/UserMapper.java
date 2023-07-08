package bd.gov.lims.user.mapper;

import bd.gov.lims.common.mapper.ConfigMapper;
import bd.gov.lims.user.dto.UserDto;
import bd.gov.lims.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}

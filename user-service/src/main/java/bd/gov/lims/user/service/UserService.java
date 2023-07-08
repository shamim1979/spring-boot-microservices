package bd.gov.lims.user.service;

import bd.gov.lims.common.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> persistUser(UserDto userDto);
}

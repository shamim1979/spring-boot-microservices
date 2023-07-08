package bd.gov.lims.user.service;

import bd.gov.lims.user.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> persistUser(UserDto userDto);
}

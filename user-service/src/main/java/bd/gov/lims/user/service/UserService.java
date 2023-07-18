package bd.gov.lims.user.service;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> persistUser(UserParam userParam);
}

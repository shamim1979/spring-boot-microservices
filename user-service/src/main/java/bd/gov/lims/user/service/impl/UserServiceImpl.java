package bd.gov.lims.user.service.impl;

import bd.gov.lims.base.config.RedisConfig;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.user.entity.AppUser;
import bd.gov.lims.user.mapper.UserMapper;
import bd.gov.lims.user.repository.UserRepository;
import bd.gov.lims.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RedisConfig redisConfig;
    @Autowired
    UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RedisConfig redisConfig) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.redisConfig = redisConfig;
    }
    @Override
    public Mono<UserDto> persistUser(UserParam userParam) {
        AppUser user = userRepository.save(userMapper.userParamToUser(userParam));
        return Mono.just(userMapper.userToUserDto(user));
    }
}

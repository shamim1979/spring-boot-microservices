package bd.gov.lims.user.service.impl;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.user.entity.AppUser;
import bd.gov.lims.user.mapper.AppUserMapper;
import bd.gov.lims.user.repository.AppUserRepository;
import bd.gov.lims.user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService<UserParam,AppUser,UserDto> {
    private final AppUserRepository userRepository;
    private final AppUserMapper userMapper;
    @Autowired
    AppUserServiceImpl(AppUserRepository userRepository, AppUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public Page<UserDto> findAll(Specification<AppUser> specification, Pageable pageable) {
        Page<AppUser> appUserPage = userRepository.findAll(specification, pageable);
        return appUserPage.map(appUser -> userMapper.entityToDto(appUser));
    }
    @Override
    public List<UserDto> findAll(Specification<AppUser> specification, Sort sort) {
        List<AppUser> appUserList = userRepository.findAll(specification, sort);
        return appUserList.stream()
                .map(appUser -> userMapper.entityToDto(appUser))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UserDto> findById(UUID id) {
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        return Optional.ofNullable(userMapper.entityToDto(optionalAppUser.orElse(null)));
    }
    @Override
    public Optional<UserDto> save(UserParam param) {
        AppUser appUser = userMapper.paramToEntity(param);
        appUser = userRepository.save(appUser);
        return Optional.ofNullable(userMapper.entityToDto(appUser));
    }
    @Override
    public Optional<UserDto> update(UUID id, UserParam param) {
        AppUser appUser = userRepository.findById(id).get();
        userMapper.paramToEntity(param, appUser);
        appUser = userRepository.save(appUser);
        return Optional.ofNullable(userMapper.entityToDto(appUser));
    }
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}

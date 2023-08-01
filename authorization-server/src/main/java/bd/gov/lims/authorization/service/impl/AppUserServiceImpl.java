package bd.gov.lims.authorization.service.impl;

import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.authorization.entity.QAppUser;
import bd.gov.lims.authorization.entity.QPrivilege;
import bd.gov.lims.authorization.entity.QRole;
import bd.gov.lims.authorization.mapper.AppUserMapper;
import bd.gov.lims.authorization.repository.AppUserRepository;
import bd.gov.lims.authorization.service.AppUserService;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
public class AppUserServiceImpl implements AppUserService<UserParam, AppUser,UserDto> {
    private final AppUserRepository userRepository;
    private final AppUserMapper userMapper;
    private final JPAQueryFactory jpaQueryFactory;
    @Autowired
    AppUserServiceImpl(AppUserRepository userRepository, AppUserMapper userMapper, JPAQueryFactory jpaQueryFactory) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jpaQueryFactory = jpaQueryFactory;
    }
    @Override
    public Optional<AppUser> loadAppUserByUsernameOrEmailOrPhone(String username) {
        QRole role = QRole.role;
        QPrivilege qPrivilege = QPrivilege.privilege;
        QAppUser qAppUser = QAppUser.appUser;
        AppUser appUser = (AppUser) jpaQueryFactory.from(qAppUser)
                .leftJoin(qAppUser.roles, role).fetchJoin()
                .leftJoin(role.privileges, qPrivilege).fetchJoin()
                .where(qAppUser.username.eq(username).or(qAppUser.email.eq(username)).or(qAppUser.phone.eq(username)))
                .fetchOne();
        return Optional.ofNullable(appUser);
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

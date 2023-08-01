package bd.gov.lims.authorization.service.impl;

import bd.gov.lims.authorization.dto.CustomUserDetails;
import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.authorization.service.AppUserService;
import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserService<UserParam,AppUser, UserDto> appUserService;
    @Autowired
    CustomUserDetailsService(AppUserService<UserParam,AppUser, UserDto> appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<AppUser> appUserOptional = appUserService.loadAppUserByUsernameOrEmailOrPhone(username);
            if (appUserOptional.isPresent()) {
                AppUser appUser = appUserOptional.get();
                return CustomUserDetails.builder()
                        .id(appUser.getId())
                        .employeeId(appUser.getEmployeeId())
                        .username(appUser.getUsername())
                        .password(appUser.getPassword())
                        .email(appUser.getEmail())
                        .phone(appUser.getPhone())
                        .accountNonExpired(appUser.isAccountNonExpired())
                        .accountNonLocked(appUser.isAccountNonLocked())
                        .credentialsNonExpired(appUser.isCredentialsNonExpired())
                        .enabled(appUser.isEnabled())
                        .verified(appUser.isVerified())
                        .deleted(appUser.isDeleted())
                        .authorities(appUser.getAuthorities())
                        .admin(appUser.isAdmin())
                        .superAdmin(appUser.isSuperAdmin())
                        .authorities(appUser.getAuthorities())
                        .build();
            }
            throw new UsernameNotFoundException("Invalid User Credentials");
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }
}

package bd.gov.lims.authorization.dto;

import bd.gov.lims.authorization.entity.Role;
import bd.gov.lims.common.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"username","password","email","phone"})
@ToString(of = {"username","password","email","phone"})
@SuperBuilder(toBuilder = true)
public class CustomUserDetails extends BaseDto implements UserDetails {
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean verified;
    private boolean deleted;
    private boolean admin;
    private boolean superAdmin;
    private UUID employeeId;
    private UUID officeId;
    private Integer failedAttempt = 0;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lockAt;
    private Set<GrantedAuthority> authorities = new LinkedHashSet<>();

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

}


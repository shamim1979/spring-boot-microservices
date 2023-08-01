package bd.gov.lims.authorization.entity;

import bd.gov.lims.authorization.enums.SystemRole;
import bd.gov.lims.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"username","password","email","phone"})
@ToString(callSuper = true, of = {"username","password","email","phone"})
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "username_idx", columnNames = {"username"})
    }, indexes = {
        @Index(name = "email_idx", columnList = "email"),
        @Index(name = "phone_idx", columnList = "phone"),
        @Index(name = "employee_id_idx", columnList = "employeeId"),
        @Index(name = "office_id_idx", columnList = "officeId")
    })
@SQLDelete(sql = "UPDATE users SET deleted=1 WHERE id=? and version=?")
@Where(clause = "deleted=0")
public class AppUser extends BaseEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = -5643478615647771471L;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Builder.Default
    private boolean accountNonExpired = Boolean.TRUE;
    @Builder.Default
    private boolean accountNonLocked = Boolean.TRUE;
    @Builder.Default
    private boolean credentialsNonExpired = Boolean.TRUE;
    @Builder.Default
    private boolean enabled = Boolean.TRUE;
    @Builder.Default
    private boolean verified = Boolean.FALSE;
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
    private UUID employeeId;
    private UUID officeId;
    @Builder.Default
    private Integer failedAttempt = 0;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lockAt;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("appUsers")
    @Builder.Default
    private Set<Role> roles = new LinkedHashSet<>();
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = getPrivilegeAuthorities(roles);
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    public Set<GrantedAuthority> getPrivilegeAuthorities(Set<Role> roles) {
        if (roles.isEmpty()) {
            return Collections.EMPTY_SET;
        }

        Set<GrantedAuthority> privilegeAuthorities = new HashSet<>();
        Set<Privilege> privileges = new HashSet<>();

        roles.forEach(role -> privileges.addAll(role.getPrivileges()));
        privileges.forEach(privilege -> privilegeAuthorities.add(new SimpleGrantedAuthority(privilege.getName())));

        return privilegeAuthorities;
    }
    public boolean isAdmin() {
        return getRoles().stream()
                .filter(role -> role.getName().matches(SystemRole.ADMIN_ROLE.name()))
                .findAny()
                .isPresent();

    }
    public boolean isSuperAdmin() {
        return getRoles().stream()
                .filter(role -> role.getName().matches(SystemRole.SUPER_ADMIN_ROLE.name()))
                .findAny()
                .isPresent();
    }

}

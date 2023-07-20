package bd.gov.lims.user.entity;

import bd.gov.lims.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

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
public class AppUser extends BaseEntity {
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

}

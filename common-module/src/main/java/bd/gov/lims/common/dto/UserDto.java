package bd.gov.lims.common.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"username","password","email","phone"})
@ToString(callSuper = true, of = {"username","password","email","phone"})
@SuperBuilder(toBuilder = true)
public class UserDto extends BaseDto{
    private String username;
    private String password;
    private String email;
    private String phone;
    private long lastLoginAt = System.currentTimeMillis();
    private boolean accountNonExpired = Boolean.TRUE;
    private boolean accountNonLocked = Boolean.TRUE;
    private boolean credentialsNonExpired = Boolean.TRUE;
    private boolean enabled = Boolean.TRUE;
    private boolean verified = Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;
    private UUID employeeId;
    private UUID officeId;
    private Integer failedAttempt;
    private LocalDateTime lockTime;
}

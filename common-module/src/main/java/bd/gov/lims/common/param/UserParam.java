package bd.gov.lims.common.param;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"username","password","email","phone"})
@ToString(callSuper = true, of = {"username","password","email","phone"})
@SuperBuilder(toBuilder = true)
public class UserParam extends BaseParam {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    private String phone;
    private UUID employeeId;
    private UUID officeId;
}


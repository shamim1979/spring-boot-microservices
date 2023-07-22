package bd.gov.lims.user.dto;

import bd.gov.lims.common.dto.BaseDto;
import bd.gov.lims.common.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display"})
@ToString(callSuper = true, of = {"name","display"})
@SuperBuilder(toBuilder = true)
public class RoleDto extends BaseDto {
    private String name;
    private String display;
    private UUID officeId;
    private int priority;
    private Set<UserDto> users = new LinkedHashSet<>();
    private Set<PrivilegeDto> privileges = new LinkedHashSet<>();

}

package bd.gov.lims.user.dto;

import bd.gov.lims.common.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display","module","subModule"})
@ToString(callSuper = true, of = {"name","display","module","subModule"})
@SuperBuilder(toBuilder = true)
public class PrivilegeDto extends BaseDto {
    private String name;
    private String display;
    private String module;
    private String subModule;
    private Set<RoleDto> roles = new LinkedHashSet<>();

}

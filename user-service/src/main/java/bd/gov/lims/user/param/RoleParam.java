package bd.gov.lims.user.param;

import bd.gov.lims.common.param.BaseParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display"})
@ToString(callSuper = true, of = {"name","display"})
@SuperBuilder(toBuilder = true)
public class RoleParam extends BaseParam {
    private String name;
    private String display;
    private UUID officeId;
    private int priority;

}

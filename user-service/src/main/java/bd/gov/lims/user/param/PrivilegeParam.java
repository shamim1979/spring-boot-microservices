package bd.gov.lims.user.param;

import bd.gov.lims.common.param.BaseParam;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display","module","subModule"})
@ToString(callSuper = true, of = {"name","display","module","subModule"})
@SuperBuilder(toBuilder = true)
public class PrivilegeParam extends BaseParam {
    private String name;
    private String display;
    private String module;
    private String subModule;

}

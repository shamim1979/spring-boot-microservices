package bd.gov.lims.base.support;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"code","message"})
@ToString(of = {"code","message"})
@Builder(toBuilder = true)
public class ApiGlobalError {
    private String code;
    private String message;

}

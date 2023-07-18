package bd.gov.lims.base.support;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"code","parameter","message"})
@ToString(of = {"code","parameter","message"})
@Builder(toBuilder = true)
public class ApiParameterError {
    private String code;
    private String parameter;
    private String message;
    private Object rejectedValue;

}

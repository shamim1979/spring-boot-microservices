package bd.gov.lims.base.support;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"code","property","message"})
@ToString(of = {"code","property","message"})
@Builder(toBuilder = true)
public class ApiFieldError {
    private String code;
    private String property;
    private String message;
    private Object rejectedValue;
    private String path;

}

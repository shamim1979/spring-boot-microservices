package bd.gov.lims.common.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"nonce","status","message"})
@ToString(of = {"nonce","status","message"})
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse {
    private Long nonce;
    private int status;
    private String message;
    private ErrorDto error;
}

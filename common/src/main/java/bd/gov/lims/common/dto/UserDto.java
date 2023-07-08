package bd.gov.lims.common.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@SuperBuilder
public class UserDto extends BaseDto{
    private String username;
    private String password;
    private String email;
}

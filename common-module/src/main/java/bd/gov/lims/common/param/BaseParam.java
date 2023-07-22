package bd.gov.lims.common.param;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@SuperBuilder(toBuilder = true)
public class BaseParam {
    private UUID createdByUser;
    private UUID updatedByUser;
    private UUID deletedByUser;
    private UUID createdByEmployee;
    private UUID updatedByEmployee;
    private UUID deletedByEmployee;
}


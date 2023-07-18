package bd.gov.lims.common.param;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@SuperBuilder(toBuilder = true)
public class BaseParam {
    private UUID id;
    @Builder.Default
    private Long version = 0L;
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
    @Builder.Default
    private boolean isActive = Boolean.TRUE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByUser;
    private UUID updatedByUser;
    private UUID deletedByUser;
    private UUID createdByEmployee;
    private UUID updatedByEmployee;
    private UUID deletedByEmployee;
    private String ipAddress;
}


package bd.gov.lims.common.dto;


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
public class BaseDto {
    private UUID id;
    @Builder.Default
    private Long version = 0L;
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
    @Builder.Default
    private boolean active = Boolean.TRUE;
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

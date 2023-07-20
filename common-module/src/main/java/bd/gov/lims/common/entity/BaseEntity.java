package bd.gov.lims.common.entity;

import bd.gov.lims.base.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4413996581711007095L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Builder.Default
    @Version
    private Long version = 0L;
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
    @Builder.Default
    private boolean active = Boolean.TRUE;
    @Basic
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Basic
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private UUID createdByUser;
    private UUID updatedByUser;
    private UUID deletedByUser;
    private UUID createdByEmployee;
    private UUID updatedByEmployee;
    private UUID deletedByEmployee;
    private String ipAddress;
    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
}

package bd.gov.lims.user.entity;

import bd.gov.lims.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
}

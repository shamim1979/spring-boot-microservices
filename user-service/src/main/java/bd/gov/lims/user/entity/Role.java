package bd.gov.lims.user.entity;

import bd.gov.lims.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display"})
@ToString(callSuper = true, of = {"name","display"})
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "roles", uniqueConstraints =
        {@UniqueConstraint(name = "name_office_id_idx", columnNames = {"name", "officeId"})})
@SQLDelete(sql = "UPDATE roles SET deleted=1 WHERE id=? and version=?")
@Where(clause = "deleted=0")
public class Role extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -3089987029403456184L;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String display;
    private UUID officeId;
    @Column(name = "priority")
    private int priority;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("roles")
    @Builder.Default
    private Set<AppUser> appUsers = new LinkedHashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("roles")
    @Builder.Default
    private Set<Privilege> privileges = new LinkedHashSet<>();

}

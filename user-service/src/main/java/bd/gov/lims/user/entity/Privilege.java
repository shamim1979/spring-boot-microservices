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

@Entity
@Table(name = "privileges", uniqueConstraints =
    {@UniqueConstraint(columnNames = {"name"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display","module","subModule"})
@ToString(callSuper = true, of = {"name","display","module","subModule"})
@SuperBuilder(toBuilder = true)
@SQLDelete(sql = "UPDATE privileges SET is_deleted=1 WHERE id=? and version=?")
@Where(clause = "is_deleted=0")
public class Privilege extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -1446963135143204966L;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "display", nullable = false)
    private String display;
    @Column(name = "module", nullable = false)
    private String module;
    @Column(name = "sub_module", nullable = false)
    private String subModule;
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("privileges")
    @Builder.Default
    private Set<Role> roles = new LinkedHashSet<>();

}

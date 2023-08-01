package bd.gov.lims.authorization.entity;

import bd.gov.lims.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, of = {"name","display","module","subModule"})
@ToString(callSuper = true, of = {"name","display","module","subModule"})
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "privileges", uniqueConstraints =
        {@UniqueConstraint(name = "name_idx", columnNames = {"name"})})
@SQLDelete(sql = "UPDATE privileges SET deleted=1 WHERE id=? and version=?")
@Where(clause = "deleted=0")
public class Privilege extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -1446963135143204966L;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String display;
    @Column(nullable = false)
    private String module;
    @Column(nullable = false)
    private String subModule;
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    @JsonBackReference
    @Builder.Default
    private Set<Role> roles = new LinkedHashSet<>();

}

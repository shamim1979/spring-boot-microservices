package bd.gov.lims.common.dto;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class TestMap {
    @Builder.Default
    private Set<Testing> testingSet = new LinkedHashSet<>();
}

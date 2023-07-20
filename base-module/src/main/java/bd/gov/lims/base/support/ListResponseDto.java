package bd.gov.lims.base.support;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
@SuperBuilder
public class ListResponseDto<S> extends BaseResponse{
    private int total;
    private List<S> payload;
}
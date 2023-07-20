package bd.gov.lims.base.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PaginationResponseDto<S> extends BaseResponse  {
    private PageDto pageable;

    private List<S> payload = new ArrayList<>();
}

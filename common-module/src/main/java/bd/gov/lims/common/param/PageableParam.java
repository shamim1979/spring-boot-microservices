package bd.gov.lims.common.param;

import bd.gov.lims.common.util.PageHelper;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"pageNo","pageSize","sortBy"})
@Builder(toBuilder = true)
public class PageableParam {
    private Integer pageNo;
    private Integer pageSize = 20;
    private String sortBy;
    public boolean isPageable() {
        if (pageNo == null) return false;
        return true;
    }
    public Pageable getPageable() {
        return PageHelper.getPageRequest(this);
    }
    public Sort getSort() {
        return PageHelper.getSort(this);
    }
}

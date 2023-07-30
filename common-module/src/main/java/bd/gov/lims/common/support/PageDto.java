package bd.gov.lims.common.support;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"pageNumber","pageSize","totalPages","totalElements"})
@ToString(of = {"pageNumber","pageSize","totalPages","totalElements"})
@Builder(toBuilder = true)
public class PageDto {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;
}

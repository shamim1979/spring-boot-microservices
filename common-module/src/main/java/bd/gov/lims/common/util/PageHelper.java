package bd.gov.lims.common.util;

import bd.gov.lims.common.constant.CommonConstant;
import bd.gov.lims.common.param.PageableParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PageHelper {
    public static PageRequest getPageRequest(PageableParam param) {
        return PageRequest.of(param.getPageNo() == null ? 0 : param.getPageNo() - 1,
                param.getPageSize(), getSort(param.getSortBy()));
    }
    public static Sort getSort(PageableParam param) {
        return getSort(param.getSortBy());
    }
    private static Sort getDefaultSort() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }
    private static Sort getSort(String sortBy) {
        return (sortBy == null || StringUtils.isBlank(sortBy)) ? getDefaultSort()
                : Sort.by(Arrays.stream(sortBy.split(CommonConstant.COMMA)).map(
                        (orders) -> getOrder(orders.split(CommonConstant.COLON)))
                .collect(Collectors.toList()));
    }
    private static Sort.Order getOrder(String[] orders) {
        return new Sort.Order(Sort.Direction.fromString(orders[1]), orders[0]);
    }

}

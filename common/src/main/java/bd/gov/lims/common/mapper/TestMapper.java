package bd.gov.lims.common.mapper;

import bd.gov.lims.common.dto.TestMap;
import bd.gov.lims.common.dto.Testing;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface TestMapper {
    TestMap mapToTestMap(Testing testing);
}

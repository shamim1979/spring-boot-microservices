package bd.gov.lims.common.support;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorDto {
    private String code;
    private String message;

    @Builder.Default
    private Map<String, Object> properties = new HashMap<>();

    @Builder.Default
    private List<ApiFieldError> fieldErrors = new ArrayList<>();

    @Builder.Default
    private List<ApiGlobalError> globalErrors = new ArrayList<>();

    @Builder.Default
    private List<ApiParameterError> parameterErrors = new ArrayList<>();
}

package bd.gov.lims.common.errorhandling;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ApiParameterError {
    private String code;
    private String parameter;
    private String message;
    private Object rejectedValue;

    public ApiParameterError(String code, String parameter, String message, Object rejectedValue) {
        this.code = code;
        this.parameter = parameter;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public String getCode() {
        return code;
    }

    public String getParameter() {
        return parameter;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}

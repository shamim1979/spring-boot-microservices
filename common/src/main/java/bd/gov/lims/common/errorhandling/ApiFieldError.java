package bd.gov.lims.common.errorhandling;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ApiFieldError {
    private String code;
    private String property;
    private String message;
    private Object rejectedValue;
    private String path;

    public ApiFieldError(String code, String property, String message, Object rejectedValue, String path) {
        this.code = code;
        this.property = property;
        this.message = message;
        this.rejectedValue = rejectedValue;
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getPath() {
        return path;
    }
}

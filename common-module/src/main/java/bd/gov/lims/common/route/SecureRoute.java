package bd.gov.lims.common.route;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class SecureRoute implements Route {
    private String path;
    private String method;
    private String[] roles;
    private String[] permissions;
}

package bd.gov.lims.base.route;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PublicRoute implements Route {
    private String path;
    private String method;
}

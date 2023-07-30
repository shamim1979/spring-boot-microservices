package bd.gov.lims.common.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class TokenExpireTime {
    private Integer tokenExpireTimeInMinutes;
    private Integer refreshTokenExpireTimeInDays;
}

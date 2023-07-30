package bd.gov.lims.common.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeUtil {
    private final TokenExpireTime tokenExpireTime;
    @Autowired
    public DateTimeUtil(TokenExpireTime tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }
    public LocalDateTime toUtcNow() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    public LocalDateTime toUtcDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    public Date jwtExpiredDateTime() {
        return Date.from(LocalDateTime.now().plusMinutes(tokenExpireTime.getTokenExpireTimeInMinutes()).atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date jwtRefreshTokenExpiredDateTime() {
        return Date.from(LocalDateTime.now().plusDays(tokenExpireTime.getRefreshTokenExpireTimeInDays()).atZone(ZoneId.systemDefault()).toInstant());
    }

}

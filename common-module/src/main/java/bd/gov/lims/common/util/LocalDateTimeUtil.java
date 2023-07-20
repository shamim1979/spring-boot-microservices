package bd.gov.lims.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalDateTimeUtil {
    public static LocalDateTime getDhakaDateTimeFromUTC(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Asia/Dhaka")).toLocalDateTime();
    }
}

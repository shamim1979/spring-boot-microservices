package bd.gov.lims.base.util;

import java.util.List;

public class StringUtils {
    public static boolean contains(List<String> list, String searchStr) {
        if (list.isEmpty()) return false;
        if (searchStr == null) return false;
        for (String str: list) {
            if (str.equals(searchStr)) return true;
        }
        return false;
    }
}

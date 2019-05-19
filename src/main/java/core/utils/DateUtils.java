package core.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class DateUtils {
    public static String formatDate(LocalDate date) {
        return DateTimeFormat.forPattern("yyyy-MM-dd").print(date);
    }

    public static DateTime parse(String dateString) {
        return DateTimeFormat.forPattern("yyyy-MM-dd")
                .parseDateTime(dateString);
    }
}


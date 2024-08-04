package pl.so5dz.aprs4j.payload.timestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

public class TimestampParser {

    public static Timestamp parse(String field) {
        if (field == null || field.isEmpty()) {
            return null;
        }
        LocalDateTime dateTime;

        if (field.endsWith(TimestampConsts.INDICATOR_HMS)) {
            int hour = Integer.parseInt(field.substring(0, 2));
            int minute = Integer.parseInt(field.substring(2, 4));
            int second = Integer.parseInt(field.substring(4, 6));
            dateTime = LocalTime.of(hour, minute, second).atDate(LocalDate.now());
            if (dateTime.isAfter(LocalDateTime.now())) {
                dateTime = dateTime.minusDays(1);
            }
        } else {
            int day = Integer.parseInt(field.substring(0, 2));
            int hour = Integer.parseInt(field.substring(2, 4));
            int minute = Integer.parseInt(field.substring(4, 6));
            var date = LocalDate.now().withDayOfMonth(day);
            if (date.isAfter(LocalDate.now())) {
                date = date.minusMonths(1);
            }
            if (field.endsWith(TimestampConsts.INDICATOR_DHM)) {
                return date.atTime(hour, minute);
            }
            if (field.endsWith(TimestampConsts.INDICATOR_UTC)) {
                return ZonedDateTime.of(date, LocalTime.of(hour, minute), ZoneOffset.UTC)
                        .toLocalDateTime();
            }

        }

        return new Timestamp(dateTime);
    }

    private static LocalDateTime parseDateTime(String field) {

        return null;
    }

}

package pl.so5dz.aprs4j.payload.timestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

public class TimestampParser {

    public static Timestamp parse(String field) {
        if (field == null || field.length() < TimestampConsts.MIN_LENGTH) {
            return null;
        }

        char formatIndicator = field.charAt(TimestampConsts.MIN_LENGTH - 1);
        TimestampStructure structure = TimestampStructure.fromIndicator(formatIndicator);
        TimestampZone zone = TimestampZone.fromIndicator(formatIndicator);
        if (structure == null || zone == null) {
            return null;
        }

        LocalDateTime dateTime;
        switch (structure) {
            case DHM:
                dateTime = parseDHM(field, zone);
                break;
            case HMS:
                dateTime = parseHMS(field);
                break;
            case MDHM:
                dateTime = parseMDHM(field);
                break;
            default:
                return null;
        }

        var timestamp = new Timestamp();
        timestamp.setDateTime(dateTime);
        timestamp.setZone(zone);
        timestamp.setStructure(structure);
        return timestamp;
    }

    private static LocalDateTime parseDHM(String field, TimestampZone zone) {
        int day = Integer.parseInt(field.substring(0, 2));
        int hour = Integer.parseInt(field.substring(2, 4));
        int minute = Integer.parseInt(field.substring(4, 6));
        var date = LocalDate.now().withDayOfMonth(day);
        if (date.isAfter(LocalDate.now())) {
            date = date.minusMonths(1);
        }
        if (zone == TimestampZone.LOCAL) {
            return date.atTime(hour, minute);
        }
        return ZonedDateTime.of(date, LocalTime.of(hour, minute), ZoneOffset.UTC)
                .toLocalDateTime();
    }

    private static LocalDateTime parseHMS(String field) {
        int hour = Integer.parseInt(field.substring(0, 2));
        int minute = Integer.parseInt(field.substring(2, 4));
        int second = Integer.parseInt(field.substring(4, 6));
        var dateTime = LocalTime.of(hour, minute, second).atDate(LocalDate.now());
        if (dateTime.isAfter(LocalDateTime.now())) {
            dateTime = dateTime.minusDays(1);
        }
        return dateTime;
    }

    private static LocalDateTime parseMDHM(String field) {
        int month = Integer.parseInt(field.substring(0, 2));
        int day = Integer.parseInt(field.substring(2, 4));
        int hour = Integer.parseInt(field.substring(4, 6));
        int minute = Integer.parseInt(field.substring(6, 8));
        var date = LocalDate.now().withMonth(month).withDayOfMonth(day);
        if (date.isAfter(LocalDate.now())) {
            date = date.minusYears(1);
        }
        return date.atTime(hour, minute);
    }
}

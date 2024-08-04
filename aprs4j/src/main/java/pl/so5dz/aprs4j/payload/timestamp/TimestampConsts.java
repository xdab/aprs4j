package pl.so5dz.aprs4j.payload.timestamp;

import java.util.regex.Pattern;

public class TimestampConsts {
    public static final char INDICATOR_DHM_LOCAL = '/';
    public static final char INDICATOR_DHM_UTC = 'z';
    public static final char INDICATOR_HMS_UTC = 'h';
    public static final Pattern TIMESTAMP_PATTERN = Pattern.compile("([0-9]{6}[zh/])|([0-9]{8})");
    public static final int MIN_LENGTH = 7;
}

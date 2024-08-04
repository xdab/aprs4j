package pl.so5dz.aprs4j.payload.timestamp;

import java.util.regex.Pattern;

public class TimestampConsts {
    public static final String INDICATOR_HMS_LOCAL = "h";
    public static final String INDICATOR_DHM_LOCAL = "/";
    public static final String INDICATOR_DHM_UTC = "z";
    public static final Pattern TIMESTAMP_PATTERN = Pattern.compile("([0-9]{6}[zh/])|([0-9]{8})");
}

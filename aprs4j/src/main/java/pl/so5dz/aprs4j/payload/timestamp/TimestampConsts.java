package pl.so5dz.aprs4j.payload.timestamp;

import java.util.regex.Pattern;

public class TimestampConsts {
    public static final char INDICATOR_DHM_LOCAL = '/';
    public static final char INDICATOR_DHM_UTC = 'z';
    public static final char INDICATOR_HMS_UTC = 'h';
    public static final int MIN_LENGTH = 7;
    public static final int MAX_LENGTH = 8;

    // Day (01-31), Hour (00-23), Minute (00-59)
    public static final Pattern TIMESTAMP_PATTERN_DHM
            = Pattern.compile("((0[1-9])|([12][0-9])|(3[01]))(([01][0-9])|2[0-3])([0-5][0-9])[z/]");

    // Hour (00-23), Minute (00-59), Second (00-59)
    public static final Pattern TIMESTAMP_PATTERN_HMS
            = Pattern.compile("(([01][0-9])|2[0-3])([0-5][0-9])([0-5][0-9])h");

    // Month (01-12), Day (01-31), Hour (00-23), Minute (00-59)
    public static final Pattern TIMESTAMP_PATTERN_MDHM
            = Pattern.compile("((0[1-9])|(1[012]))((0[1-9])|([12][0-9])|(3[01]))(([01][0-9])|2[0-3])([0-5][0-9])");
}

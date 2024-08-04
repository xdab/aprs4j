package pl.so5dz.aprs4j.payload.timestamp;

import java.util.regex.Pattern;

public enum TimestampStructure {
    DHM(7, TimestampConsts.TIMESTAMP_PATTERN_DHM),
    HMS(7, TimestampConsts.TIMESTAMP_PATTERN_HMS),
    MDHM(8, TimestampConsts.TIMESTAMP_PATTERN_MDHM);

    private final int length;
    private final Pattern pattern;

    TimestampStructure(int length, Pattern pattern) {
        this.length = length;
        this.pattern = pattern;
    }

    public int getLength() {
        return length;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static TimestampStructure fromIndicator(char indicator) {
        if (indicator == TimestampConsts.INDICATOR_HMS_UTC) {
            return HMS;
        }
        if (indicator == TimestampConsts.INDICATOR_DHM_LOCAL || indicator == TimestampConsts.INDICATOR_DHM_UTC) {
            return DHM;
        }
        if (Character.isDigit(indicator)) {
            return MDHM;
        }
        return null;
    }
}

package pl.so5dz.aprs4j.payload.timestamp;

public enum TimestampZone {
    LOCAL,
    UTC;

    public static TimestampZone fromIndicator(char indicator) {
        if (indicator == TimestampConsts.INDICATOR_DHM_LOCAL) {
            return LOCAL;
        }
        if (indicator == TimestampConsts.INDICATOR_HMS_UTC || indicator == TimestampConsts.INDICATOR_DHM_UTC || Character.isDigit(indicator)) {
            return UTC;
        }
        return null;
    }
}

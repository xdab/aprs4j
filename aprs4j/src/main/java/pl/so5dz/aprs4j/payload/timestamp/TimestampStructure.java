package pl.so5dz.aprs4j.payload.timestamp;

public enum TimestampStructure {
    DHM(7),
    HMS(7),
    MDHM(8);

    private final int length;

    TimestampStructure(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
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

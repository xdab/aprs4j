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
}

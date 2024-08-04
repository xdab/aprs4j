package pl.so5dz.aprs4j.payload.timestamp;

import java.time.LocalDateTime;

public class Timestamp {
    LocalDateTime dateTime;
    TimestampZone zone;
    TimestampStructure structure;

    public Timestamp() {
    }

    public static Timestamp of(String substring) {
        return TimestampParser.parse(substring);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TimestampZone getZone() {
        return zone;
    }

    public void setZone(TimestampZone zone) {
        this.zone = zone;
    }

    public TimestampStructure getStructure() {
        return structure;
    }

    public void setStructure(TimestampStructure structure) {
        this.structure = structure;
    }
}

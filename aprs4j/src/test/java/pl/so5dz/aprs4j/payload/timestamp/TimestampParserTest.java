package pl.so5dz.aprs4j.payload.timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TimestampParserTest {

    @Test
    public void validTimestamp_DHM_UTC() {
        Timestamp timestamp = TimestampParser.parse("121314z");
        assertNotNull(timestamp);
        assertEquals(TimestampStructure.DHM, timestamp.getStructure());
        assertEquals(TimestampZone.UTC, timestamp.getZone());
        assertEquals(12, timestamp.getDateTime().getDayOfMonth());
        assertEquals(13, timestamp.getDateTime().getHour());
        assertEquals(14, timestamp.getDateTime().getMinute());
    }

    @Test
    public void validTimestamp_DHM_LOCAL() {
        Timestamp timestamp = TimestampParser.parse("121314/");
        assertNotNull(timestamp);
        assertEquals(TimestampStructure.DHM, timestamp.getStructure());
        assertEquals(TimestampZone.LOCAL, timestamp.getZone());
        assertEquals(12, timestamp.getDateTime().getDayOfMonth());
        assertEquals(13, timestamp.getDateTime().getHour());
        assertEquals(14, timestamp.getDateTime().getMinute());
    }

    @Test
    public void validTimestamp_HMS_UTC() {
        Timestamp timestamp = TimestampParser.parse("131415h");
        assertNotNull(timestamp);
        assertEquals(TimestampStructure.HMS, timestamp.getStructure());
        assertEquals(TimestampZone.UTC, timestamp.getZone());
        assertEquals(13, timestamp.getDateTime().getHour());
        assertEquals(14, timestamp.getDateTime().getMinute());
        assertEquals(15, timestamp.getDateTime().getSecond());
    }

    @Test
    public void validTimestamp_MDHM() {
        Timestamp timestamp = TimestampParser.parse("01211314");
        assertNotNull(timestamp);
        assertEquals(TimestampStructure.MDHM, timestamp.getStructure());
        assertEquals(TimestampZone.UTC, timestamp.getZone());
        assertEquals(1, timestamp.getDateTime().getMonthValue());
        assertEquals(21, timestamp.getDateTime().getDayOfMonth());
        assertEquals(13, timestamp.getDateTime().getHour());
        assertEquals(14, timestamp.getDateTime().getMinute());
    }

    @Test
    public void nullTimestamp() {
        Timestamp timestamp = TimestampParser.parse(null);
        assertNull(timestamp);
    }

    @Test
    public void emptyTimestamp() {
        Timestamp timestamp = TimestampParser.parse("");
        assertNull(timestamp);
    }

    @Test
    public void invalidTimestamp() {
        Timestamp timestamp = TimestampParser.parse("ABCDEFGHIJ");
        assertNull(timestamp);
    }

    @Test
    public void tooShortTimestamp() {
        Timestamp timestamp = TimestampParser.parse("1");
        assertNull(timestamp);
    }
}

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
        assertEquals(timestamp.getDateTime().getDayOfMonth(), 12);
        assertEquals(timestamp.getDateTime().getHour(), 13);
        assertEquals(timestamp.getDateTime().getMinute(), 14);
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
        Timestamp timestamp = TimestampParser.parse("ABCDEFG");
        assertNull(timestamp);
    }

}

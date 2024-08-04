package pl.so5dz.aprs4j.payload.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StatusParserTest {

    @Test
    public void validStatus() {
        Status status = StatusParser.parse(">Test");
        assertNotNull(status);
        assertEquals(status.getComment(), "Test");
    }

    @Test
    public void validStatusWithTimestamp() {
        Status status = StatusParser.parse(">121212zTest");
        assertNotNull(status);
        assertEquals(status.getComment(), "Test");
        assertNotNull(status.getTimestamp());
    }

    @Test
    public void nullStatus() {
        Status status = StatusParser.parse(null);
        assertNull(status);
    }

    @Test
    public void emptyStatus() {
        Status status = StatusParser.parse("");
        assertNull(status);
    }

    @Test
    public void invalidStatus() {
        Status status = StatusParser.parse("Test");
        assertNull(status);
    }
}

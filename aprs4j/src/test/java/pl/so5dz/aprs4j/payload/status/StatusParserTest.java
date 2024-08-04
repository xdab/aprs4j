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
        assertNull(status.getTimestamp());
    }

    @Test
    public void validStatusWithTimestamp() {
        Status status = StatusParser.parse(">121314zTest");
        assertNotNull(status);
        assertEquals("Test", status.getComment());
        assertNotNull(status.getTimestamp());
    }

    @Test
    public void validStatusInvalidTimestamp() {
        Status status = StatusParser.parse(">01211530Test");
        assertNotNull(status);
        assertEquals("01211530Test", status.getComment());
        assertNull(status.getTimestamp());
    }

    @Test
    public void emptyStatus() {
        Status status = StatusParser.parse(">");
        assertNotNull(status);
        assertEquals("", status.getComment());
        assertNull(status.getTimestamp());
    }

    @Test
    public void nullInfo() {
        Status status = StatusParser.parse(null);
        assertNull(status);
    }

    @Test
    public void emptyInfo() {
        Status status = StatusParser.parse("");
        assertNull(status);
    }

    @Test
    public void notAStatus() {
        Status status = StatusParser.parse("Test");
        assertNull(status);
    }
}

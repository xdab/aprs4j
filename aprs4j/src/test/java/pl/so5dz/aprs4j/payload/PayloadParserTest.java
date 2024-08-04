package pl.so5dz.aprs4j.payload;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PayloadParserTest {

    @Test
    public void status() {
        Payload payload = PayloadParser.parse(">Test");
        assertNotNull(payload);
        assertNotNull(payload.getStatus());
        assertNull(payload.getPosition());
    }

    @Test
    public void position() {
        Payload payload = PayloadParser.parse("!5210.00N/02103.00W-Test");
        assertNotNull(payload);
        assertNull(payload.getStatus());
        assertNotNull(payload.getPosition());
    }

    @Test
    public void unknown() {
        Payload payload = PayloadParser.parse("unknown");
        assertNotNull(payload);
        assertEquals(payload.getUnparsed(), "unknown");
    }

    @Test
    public void nullPayload() {
        Payload payload = PayloadParser.parse(null);
        assertNotNull(payload);
        assertNull(payload.getStatus());
        assertNull(payload.getUnparsed());
    }

    @Test
    public void emptyPayload() {
        Payload payload = PayloadParser.parse("");
        assertNotNull(payload);
        assertNull(payload.getStatus());
        assertNull(payload.getUnparsed());
    }

}

package pl.so5dz.aprs4j.payload.position;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {
    private static final double DELTA = 0.00001;

    @Test
    void valid_NT_NM() {
        var info = "!5210.00N/02103.00W-Test";
        var position = PositionParser.parse(info);
        assertNotNull(position);
        assertFalse(position.isMessagingCapable());
        assertNull(position.getTimestamp());
        assertEquals(52.16666667, position.getLatitude(), DELTA);
        assertEquals(-21.05, position.getLongitude(), DELTA);
        assertEquals("/-", position.getSymbol());
        assertEquals("Test", position.getComment());
    }

    @Test
    void valid_NT_M() {
        var info = "=5210.00S/02103.00W-Test";
        var position = PositionParser.parse(info);
        assertNotNull(position);
        assertTrue(position.isMessagingCapable());
        assertNull(position.getTimestamp());
        assertEquals("/-", position.getSymbol());
        assertEquals("Test", position.getComment());
    }

    @Test
    void valid_T_M() {
        var info = "@092345/5210.00S/02103.00E-Test";
        var position = PositionParser.parse(info);
        assertNotNull(position);
        assertTrue(position.isMessagingCapable());
        assertNotNull(position.getTimestamp());
        assertEquals("/-", position.getSymbol());
        assertEquals("Test", position.getComment());
    }

    @Test
    void valid_T_NM() {
        var info = "/092345z5210.00N/02103.00E-Test";
        var position = PositionParser.parse(info);
        assertNotNull(position);
        assertFalse(position.isMessagingCapable());
        assertNotNull(position.getTimestamp());
        assertEquals("/-", position.getSymbol());
        assertEquals("Test", position.getComment());
    }

    @Test
    void nullInput() {
        assertNull(PositionParser.parse(null));
    }

    @Test
    void tooShort() {
        assertNull(PositionParser.parse("!1234"));
    }

    @Test
    void invalidIndicator() {
        assertNull(PositionParser.parse("X1234.56N/12345.67W-Test"));
    }

    @Nested
    public class ParseLatitudeTest {
        @Test
        void northernHemisphere() {
            assertEquals(49.058333333333, PositionParser.parseLatitude("4903.50N"), DELTA);
        }

        @Test
        void southernHemisphere() {
            assertEquals(-33.91683333333, PositionParser.parseLatitude("3355.01S"), DELTA);
        }
    }

    @Nested
    public class ParseLongitudeTest {
        @Test
        void easternHemisphere() {
            assertEquals(72.029166666666, PositionParser.parseLongitude("07201.75E"), DELTA);
        }

        @Test
        void westernHemisphere() {
            assertEquals(-122.089, PositionParser.parseLongitude("12205.34W"), DELTA);
        }
    }
}
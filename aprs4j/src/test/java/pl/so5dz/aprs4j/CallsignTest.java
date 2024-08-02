package pl.so5dz.aprs4j;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallsignTest {

    @Nested
    public class CallsignOfTest {

        @Test
        void validCallsign() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("AB1CDE-15"));
            assertEquals("AB1CDE", callsign.getBase());
            assertEquals(15, callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void validDigipeatingScheme() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("WIDE2-2"));
            assertEquals("WIDE2", callsign.base);
            assertEquals(2, callsign.ssid);
            assertFalse(callsign.repeated);
        }

        @Test
        void validQConstruct() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("qAR"));
            assertEquals("qAR", callsign.base);
            assertNull(callsign.ssid);
            assertFalse(callsign.repeated);
        }

        @Test
        void validExhaustedDigipeatingScheme() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("XR1*"));
            assertEquals("XR1", callsign.base);
            assertNull(callsign.ssid);
            assertTrue(callsign.repeated);
        }

        @Test
        void validCallsignWithoutSSID() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("AB1CDE"));
            assertEquals("AB1CDE", callsign.base);
            assertNull(callsign.ssid);
            assertFalse(callsign.repeated);
        }

        @Test
        void validCallsignWithRepeatedMarker() {
            Callsign callsign = assertDoesNotThrow(() -> Callsign.of("XY2Z*"));
            assertEquals("XY2Z", callsign.base);
            assertNull(callsign.ssid);
            assertTrue(callsign.repeated);
        }

        @Test
        void nullCallsign() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Callsign.of(null));
            assertEquals("Invalid callsign: null", e.getMessage());
        }

        @Test
        void invalidSSIDNotANumber() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Callsign.of("AB1CDE-1A"));
            assertEquals("Invalid callsign: AB1CDE-1A (SSID not a number)", e.getMessage());
        }

        @Test
        void tooManySeparators() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Callsign.of("AB1C-1-1"));
            assertEquals("Invalid callsign: AB1C-1-1 (too many separators)", e.getMessage());
        }
    }

    @Nested
    public class CallsignSetBaseTest {
        @Test
        void validBase() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setBase("XY2Z");
            assertEquals("XY2Z", callsign.getBase());
            assertEquals(15, callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void validQConstruct() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setBase("qAR");
            assertEquals("qAR", callsign.getBase());
            assertEquals(15, callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void nullBase() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setBase(null));
            assertEquals("Invalid callsign base: null", e.getMessage());
        }

        @Test
        void tooShortBase() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setBase("A"));
            assertEquals("Invalid callsign base: A (too short)", e.getMessage());
        }

        @Test
        void tooLongBase() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setBase("ABCDEFGHIJ"));
            assertEquals("Invalid callsign base: ABCDEFGHIJ (too long)", e.getMessage());
        }

        @Test
        void invalidBase() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setBase("AB-CDE"));
            assertEquals("Invalid callsign base: AB-CDE (not valid)", e.getMessage());
        }

        @Test
        void invalidBase2() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setBase("wAB1CD"));
            assertEquals("Invalid callsign base: wAB1CD (not valid)", e.getMessage());
        }
    }

    @Nested
    public class CallsignSetSSIDTest {
        @Test
        void validSSID() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setSSID(10);
            assertEquals("AB1CDE", callsign.getBase());
            assertEquals(10, callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void validSSIDZero() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setSSID(0);
            assertEquals("AB1CDE", callsign.getBase());
            assertNull(callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void validSSIDNull() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setSSID(null);
            assertEquals("AB1CDE", callsign.getBase());
            assertNull(callsign.getSSID());
            assertFalse(callsign.isRepeated());
        }

        @Test
        void tooLargeSSID() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setSSID(16));
            assertEquals("Invalid callsign SSID: 16 (out of valid range)", e.getMessage());
        }

        @Test
        void negativeSSID() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> callsign.setSSID(-1));
            assertEquals("Invalid callsign SSID: -1 (out of valid range)", e.getMessage());
        }
    }

    @Nested
    public class CallsignSetRepeatedTest {
        @Test
        void validRepeated() {
            Callsign callsign = new Callsign("AB1CDE", 15, false);
            callsign.setRepeated(true);
            assertEquals("AB1CDE", callsign.getBase());
            assertEquals(15, callsign.getSSID());
            assertTrue(callsign.isRepeated());
        }
    }
}
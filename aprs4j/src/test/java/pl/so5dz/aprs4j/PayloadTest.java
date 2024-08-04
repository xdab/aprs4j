package pl.so5dz.aprs4j;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.so5dz.aprs4j.payload.Payload;

import static org.junit.jupiter.api.Assertions.*;

class PayloadTest {

    @Nested
    public class PayloadStatusTest {

            @Test
            void validStatus() {
                Payload payload = assertDoesNotThrow(() -> Payload.of(">status"));
                assertEquals("status", payload.getComment());
            }
    }
}
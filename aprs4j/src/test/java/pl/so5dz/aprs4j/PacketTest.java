package pl.so5dz.aprs4j;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacketTest {

    @Nested
    public class PacketOfStructureTest {

        @Test
        void validPacket() {
            Packet packet = assertDoesNotThrow(() -> Packet.of("AB1>XY2,RPT1*,RPT2*,RPT3*:Hello World!"));
            assertEquals("AB1", packet.source.toString());
            assertEquals("XY2", packet.destination.toString());
            assertEquals(3, packet.path.size());
            assertEquals("RPT1*", packet.path.get(0).toString());
            assertEquals("RPT2*", packet.path.get(1).toString());
            assertEquals("RPT3*", packet.path.get(2).toString());
        }

        @Test
        void validPacketWithNoRepeaters() {
            Packet packet = assertDoesNotThrow(() -> Packet.of("AB1-1>XY2-2:Hello World!"));
            assertEquals("AB1-1", packet.source.toString());
            assertEquals("XY2-2", packet.destination.toString());
            assertEquals(0, packet.path.size());
        }

        @Test
        void nullPacket() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Packet.of(null));
            assertEquals("Invalid packet: null", e.getMessage());
        }

        @Test
        void packetWithNoDestination() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Packet.of("AB1CDE-15,TCPIP*:Hello World!"));
            assertEquals("Invalid packet: AB1CDE-15,TCPIP*:Hello World! (missing destination separator)", e.getMessage());
        }

        @Test
        void packetWithNoInfo() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Packet.of("AB1CDE-15>APRS,TCPIP*"));
            assertEquals("Invalid packet: AB1CDE-15>APRS,TCPIP* (missing info separator)", e.getMessage());
        }

        @Test
        void tooShortPacket() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Packet.of("AB1>APR"));
            assertEquals("Invalid packet: AB1>APR (too short)", e.getMessage());
        }

        @Test
        void tooLongPacket() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Packet.of("AB1CDE-15>APRS,TCPIP*:0000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111000001111100000111110000011111"));
            assertTrue(e.getMessage().endsWith("(too long)"));
        }
    }
}
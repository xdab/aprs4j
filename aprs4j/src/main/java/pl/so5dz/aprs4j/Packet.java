package pl.so5dz.aprs4j;

import pl.so5dz.aprs4j.payload.Payload;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an APRS packet. <br>
 * Constructor is package-private, use {@link #of(String)} to create a new instance. <br>
 */
public class Packet {
    Callsign source;
    Callsign destination;
    List<Callsign> path;
    String info;
    Payload payload;

    Packet(Callsign source, Callsign destination, List<Callsign> path, String info) {
        this.source = source;
        this.destination = destination;
        this.path = path;
        this.info = info;
    }

    /**
     * Creates a new {@link Packet} from a string in TNC2 format.
     *
     * @param str the string to parse
     *
     * @return the new {@link Packet}
     *
     * @throws IllegalArgumentException if the string is not a valid TNC2 packet
     */
    public static Packet of(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Invalid packet: null");
        }
        str = str.trim(); // Remove leading and trailing whitespace

        if (str.length() < PacketConsts.MIN_LENGTH) {
            throw new IllegalArgumentException("Invalid packet: " + str + " (too short)");
        }
        if (str.length() > PacketConsts.MAX_LENGTH) {
            throw new IllegalArgumentException("Invalid packet: " + str + " (too long)");
        }

        // Split the packet into routing information and payload
        String[] parts = str.split(PacketConsts.INFO_SEPARATOR, 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid packet: " + str + " (missing info separator)");
        }
        String routing = parts[0];
        String info = parts[1];

        // Split the routing part into source and path (including destination)
        String[] routingParts = routing.split(PacketConsts.DESTINATION_SEPARATOR, 2);
        if (routingParts.length < 2) {
            throw new IllegalArgumentException("Invalid packet: " + str + " (missing destination separator)");
        }
        Callsign source = Callsign.of(routingParts[0]);

        // Split the path into individual callsigns, first of which is the destination
        String[] pathParts = routingParts[1].split(PacketConsts.PATH_SEPARATOR);
        Callsign destination = Callsign.of(pathParts[0]);
        List<Callsign> path = new ArrayList<>();
        for (int i = 1; i < pathParts.length; i++) {
            path.add(Callsign.of(pathParts[i]));
        }

        return new Packet(source, destination, path, info);
    }

    public Callsign getSource() {
        return source;
    }

    public Callsign getDestination() {
        return destination;
    }

    public List<Callsign> getPath() {
        return path;
    }

    public String getInfo() {
        return info;
    }

    /**
     * Returns the payload of this packet.
     * The payload is lazily initialized (parsed) and the result cached for subsequent calls.
     *
     * @return the payload
     */
    public Payload getPayload() {
        return payload;
    }
}

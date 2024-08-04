package pl.so5dz.aprs4j.payload;

import pl.so5dz.aprs4j.payload.position.Position;
import pl.so5dz.aprs4j.payload.status.Status;


/**
 * Represents an APRS packet payload.
 * Constructor is package-private, use {@link #of(String)} to create a new
 * instance. <br>
 */
public class Payload {
    private Status status;
    private Position position;
    private String unparsed;

    /**
     * Creates a new {@link Payload} from a string.
     *
     * @param info the string to parse
     * @return the new {@link Payload}
     */
    public static Payload of(String info) {
        return PayloadParser.parse(info);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUnparsed() {
        return unparsed;
    }

    public void setUnparsed(String unparsed) {
        this.unparsed = unparsed;
    }
}

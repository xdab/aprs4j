package pl.so5dz.aprs4j.payload;

/**
 * Represents an APRS packet payload.
 * Constructor is package-private, use {@link #of(String)} to create a new instance. <br>
 */
public class Payload {
    private Status status;
    private String comment;

    /**
     * Creates a new {@link Payload} from a string.
     *
     * @param info the string to parse
     * @return the new {@link Payload}
     */
    public static Payload of(String info) {
        var payload = new Payload();
        payload.setComment(info.substring(1));
        return payload;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

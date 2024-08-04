package pl.so5dz.aprs4j.payload.status;

import pl.so5dz.aprs4j.payload.timestamp.Timestamp;

public class Status {
    private Timestamp timestamp;
    private String comment;

    public Status() {
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static Status of(String substring) {
        return StatusParser.parse(substring);
    }
}

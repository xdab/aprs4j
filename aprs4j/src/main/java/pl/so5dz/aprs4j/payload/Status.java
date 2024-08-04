package pl.so5dz.aprs4j.payload;

public class Status {
    private String comment;

    public Status() {
    }

    public Status(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

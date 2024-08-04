package pl.so5dz.aprs4j.payload.position;

import pl.so5dz.aprs4j.payload.timestamp.Timestamp;

public class Position {
    private boolean messagingCapable;
    private Timestamp timestamp;
    private double latitude;
    private double longitude;
    private String symbol;
    private String comment;

    public Position() {
    }

    public static Position of(String info) {
        return PositionParser.parse(info);
    }

    public boolean isMessagingCapable() {
        return messagingCapable;
    }

    public void setMessagingCapable(boolean messagingCapable) {
        this.messagingCapable = messagingCapable;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

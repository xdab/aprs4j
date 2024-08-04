package pl.so5dz.aprs4j.payload.status;

import pl.so5dz.aprs4j.payload.timestamp.Timestamp;
import pl.so5dz.aprs4j.payload.timestamp.TimestampConsts;
import pl.so5dz.aprs4j.payload.timestamp.TimestampStructure;

public class StatusParser {
    public static Status parse(String info) {
        if (info == null || info.isEmpty()) {
            return null;
        }
        if (info.charAt(0) != StatusConsts.INDICATOR) {
            return null;
        }
        String comment = info.substring(1);
        Timestamp timestamp = null;

        String timestampField = extractTimestamp(comment);
        if (timestampField != null) {
            timestamp = Timestamp.of(timestampField);
            comment = comment.substring(timestamp.getStructure().getLength());
        }

        var status = new Status();
        status.setComment(comment);
        status.setTimestamp(timestamp);
        return status;
    }

    private static String extractTimestamp(String comment) {
        if (comment.length() < TimestampConsts.MIN_LENGTH) {
            return null;
        }

        // Status report may only contain a DHM timestamp
        String potentialTimestamp = comment.substring(0, TimestampConsts.MIN_LENGTH);
        if (TimestampStructure.DHM.getPattern().matcher(potentialTimestamp).matches()) {
            return potentialTimestamp;
        }

        return null;
    }
}

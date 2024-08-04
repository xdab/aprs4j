package pl.so5dz.aprs4j.payload.position;

import pl.so5dz.aprs4j.payload.timestamp.Timestamp;
import pl.so5dz.aprs4j.payload.timestamp.TimestampConsts;

public class PositionParser {
    public static Position parse(String info) {
        if (info == null || info.length() < PositionConsts.MIN_LENGTH) {
            return null;
        }

        boolean messagingCapable, hasTimestamp;
        char dataTypeIndicator = info.charAt(0);
        if (dataTypeIndicator == PositionConsts.INDICATOR_NT_NM) {
            hasTimestamp = false;
            messagingCapable = false;
        } else if (dataTypeIndicator == PositionConsts.INDICATOR_NT_M) {
            hasTimestamp = false;
            messagingCapable = true;
        } else if (dataTypeIndicator == PositionConsts.INDICATOR_T_NM) {
            hasTimestamp = true;
            messagingCapable = false;
        } else if (dataTypeIndicator == PositionConsts.INDICATOR_T_M) {
            hasTimestamp = true;
            messagingCapable = true;
        } else {
            return null;
        }
        info = info.substring(1);

        var position = new Position();
        position.setMessagingCapable(messagingCapable);

        Timestamp timestamp = null;
        if (hasTimestamp) {
            String timestampField = info.substring(0, TimestampConsts.MAX_LENGTH);
            timestamp = Timestamp.of(timestampField);
            if (timestamp != null) {
                info = info.substring(timestamp.getStructure().getLength());
            }
        }
        position.setTimestamp(timestamp);

        double latitude = parseLatitude(info.substring(0, 8));
        double longitude = parseLongitude(info.substring(9, 18));
        position.setLatitude(latitude);
        position.setLongitude(longitude);

        char symbolTable = info.charAt(8);
        char symbolCode = info.charAt(18);
        String symbol = Character.toString(symbolTable) + symbolCode;
        position.setSymbol(symbol);

        String comment = info.substring(19);
        position.setComment(comment);

        return position;
    }

    static double parseLatitude(String latitudeField) {
        int latDegrees = Integer.parseInt(latitudeField.substring(0, 2));
        int latMinutes = Integer.parseInt(latitudeField.substring(2, 4));
        int latMinutesFraction = Integer.parseInt(latitudeField.substring(5, 7));
        double latitude = latDegrees + latMinutes / 60.0 + latMinutesFraction / 6000.0;
        if (latitudeField.charAt(7) == PositionConsts.SOUTH) {
            latitude = -latitude;
        }
        return latitude;
    }

    static double parseLongitude(String longitudeField) {
        int lonDegrees = Integer.parseInt(longitudeField.substring(0, 3));
        int lonMinutes = Integer.parseInt(longitudeField.substring(3, 5));
        int lonMinutesFraction = Integer.parseInt(longitudeField.substring(6, 8));
        double longitude = lonDegrees + lonMinutes / 60.0 + lonMinutesFraction / 6000.0;
        if (longitudeField.charAt(8) == PositionConsts.WEST) {
            longitude = -longitude;
        }
        return longitude;
    }
}

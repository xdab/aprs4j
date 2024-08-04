package pl.so5dz.aprs4j.payload;

import pl.so5dz.aprs4j.payload.status.Status;

class PayloadParser {
    public static Payload parse(String info) {
        var payload = new Payload();
        if (info == null || info.isEmpty()) {
            return payload;
        }

        char dataTypeIndicator = info.charAt(0);
        var dataType = DataType.fromIndicator(dataTypeIndicator);
        switch (dataType) {
            case STATUS:
                payload.setStatus(Status.of(info));
                break;
            default:
                payload.setUnparsed(info);
        }
        return payload;
    }
}

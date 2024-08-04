package pl.so5dz.aprs4j.payload;

import java.util.Set;

public enum DataType {
    POSITION('!', '=', '@', '/'),
    ITEM(')'),
    OBJECT(';'),
    STATUS('>'),
    MIC_E('`', '\'', '\u001c', '\u001d'),
    THIRD_PARTY('}'),
    TELEMETRY('T'),
    MESSAGE_LIKE(':'),
    QUERY('?');

    private final Set<Character> indicators;

    DataType(Character... indicators) {
        this.indicators = Set.of(indicators);
    }
}

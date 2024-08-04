package pl.so5dz.aprs4j.payload;

import java.util.Set;

import pl.so5dz.aprs4j.payload.position.PositionConsts;
import pl.so5dz.aprs4j.payload.status.StatusConsts;

public enum DataType {
    POSITION(PositionConsts.INDICATOR_T_M,
            PositionConsts.INDICATOR_NT_M,
            PositionConsts.INDICATOR_NT_NM,
            PositionConsts.INDICATOR_T_NM),

    STATUS(StatusConsts.INDICATOR),

    ITEM(')'),
    OBJECT(';'),
    MIC_E('`', '\'', '\u001c', '\u001d'),
    THIRD_PARTY('}'),
    TELEMETRY('T'),
    MESSAGE_LIKE(':'),
    QUERY('?'),
    UNKNOWN();

    private final Set<Character> indicators;

    DataType(Character... indicators) {
        this.indicators = Set.of(indicators);
    }

    public Set<Character> getIndicators() {
        return indicators;
    }

    public static DataType fromIndicator(char indicator) {
        for (var dataType : values()) {
            if (dataType.getIndicators().contains(indicator)) {
                return dataType;
            }
        }
        return UNKNOWN;
    }
}

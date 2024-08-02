package pl.so5dz.aprs4j;

import java.util.regex.Pattern;

class CallsignConsts {
    // Structure
    static final int MIN_LENGTH = 3;
    static final int MAX_LENGTH = 9;
    static final String SSID_SEPARATOR = "-";
    static final int MIN_SSID = 0;
    static final int MAX_SSID = 15;
    static final String REPEATED_MARKER = "*";
    static final Pattern VALID_BASE = Pattern.compile("(q[A-Z]{2,})|([A-Z][A-Z0-9]{2,8})");
}

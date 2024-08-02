package pl.so5dz.aprs4j;


/**
 * Represents an APRS callsign as found in a TNC2-formatted packet. <br>
 * Constructor is package-private, use {@link #of(String)} to create a new instance. <br>
 *
 * <p>
 *     Callsigns are composed of a base, an optional SSID and an optional repeated marker. <br>
 *     Details of each part can be found in the documentation of it's dedicated setter. <br>
 *     (see {@link #setBase(String)}, {@link #setSSID(Integer)}, {@link #setRepeated(boolean)}) <br>
 * </p>
 *
 * <p>
 *     Example callsigns:
 *     <ul>
 *         <li>AB1CDE <i>(main station callsign, non-repeated)</i></li>
 *         <li>AB1CDE-1* <i>(substation 1, repeated)</i></li>
 *         <li>WIDE2-2 <i>(special: digipeating instruction)</i></li>
 *         <li>qAR <i>(special: APRS-IS q-construct)</i></li>
 *     </ul>
 */
public class Callsign {
    String base;
    Integer ssid;
    boolean repeated;

    Callsign(String base, Integer ssid, boolean repeated) {
        this.base = base;
        this.ssid = ssid;
        this.repeated = repeated;
    }

    /**
     * Creates a new {@link Callsign} from a string in TNC2 format.
     *
     * @param str the string to parse
     *
     * @return the new {@link Callsign}
     *
     * @throws IllegalArgumentException if the string is not a valid TNC2 callsign
     */
    public static Callsign of(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Invalid callsign: null");
        }
        str = str.trim(); // Remove leading and trailing whitespace

        boolean repeated = str.endsWith(CallsignConsts.REPEATED_MARKER);
        if (repeated) {
            // Remove the repeated marker
            str = str.substring(0, str.length() - 1);
        }

        var callsign = new Callsign(null, null, repeated);

        String[] parts = str.split(CallsignConsts.SSID_SEPARATOR);
        if (parts.length > 2) {
            throw new IllegalArgumentException("Invalid callsign: " + str + " (too many separators)");
        }
        callsign.setBase(parts[0]);
        if (parts.length == 2) {
            try {
                callsign.setSSID(Integer.parseInt(parts[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid callsign: " + str + " (SSID not a number)");
            }
        }

        return callsign;
    }

    /**
     * Returns the callsign base (legal callsign).
     *
     * @return the callsign base of this callsign object
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the callsign base. <br>

     * <p>
     *     The base is the main part of the callsign, and may be a legal callsign,
     *     a q-construct or a digipeating instruction. <br>
     *     It is always required, and must be between 3 and 9 characters long. <br>
     * </p>
     *
     * <p>
     *     Legal callsigns and digipeating instructions are composed of uppercase letters and digits. <br>
     *     The first character must be a letter. <br>
     * </p>
     *
     * <p>
     *     q-constructs are composed of a lowercase 'q' followed by exactly 2 uppercase letters.
     * </p>
     *
     * @param base the new callsign base
     *
     * @throws IllegalArgumentException if the base is {@code null}, too short, too long or otherwise not valid
     */
    public void setBase(String base) {
        if (base == null) {
            throw new IllegalArgumentException("Invalid callsign base: null");
        }
        base = base.trim(); // Remove leading and trailing whitespace
        if (base.length() < CallsignConsts.MIN_LENGTH) {
            throw new IllegalArgumentException("Invalid callsign base: " + base + " (too short)");
        }
        if (base.length() > CallsignConsts.MAX_LENGTH) {
            throw new IllegalArgumentException("Invalid callsign base: " + base + " (too long)");
        }
        if (!CallsignConsts.VALID_BASE.matcher(base).matches()) {
            throw new IllegalArgumentException("Invalid callsign base: " + base + " (not valid)");
        }
        this.base = base;
    }

    /**
     * Returns the SSID specified in the callsign.
     *
     * @return the SSID specified in the callsign, or {@code null} if the callsign has no SSID
     */
    public Integer getSSID() {
        return ssid;
    }

    /**
     * Sets the SSID in the callsign.
     *
     * <p>
     *     The SSID is an optional part of the callsign, separated from the base by a hyphen. <br>
     *     It is a number between 1 and 15 (inclusive). 0 is accepted, but considered an "absent" value equivalent to {@code null}. <br>
     * </p>
     *
     * @param ssid the new SSID
     *             or 0/{@code null} if the callsign should have no SSID
     *
     * @throws IllegalArgumentException if the SSID is not a number or is out of the valid range
     */
    public void setSSID(Integer ssid) {
        if (ssid != null) {
            if (ssid < CallsignConsts.MIN_SSID || ssid > CallsignConsts.MAX_SSID) {
                throw new IllegalArgumentException("Invalid callsign SSID: " + ssid + " (out of valid range)");
            }
            if (ssid == 0) {
                ssid = null;
            }
        }
        this.ssid = ssid;
    }

    /**
     * Returns whether the callsign is marked as repeated.
     *
     * @return {@code true} if the callsign is repeated, {@code false} otherwise
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Sets a flag indicating the callsign has acted as a repeater.
     *
     * @param repeated {@code true} if the callsign is repeated, {@code false} otherwise
     */
    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }
}

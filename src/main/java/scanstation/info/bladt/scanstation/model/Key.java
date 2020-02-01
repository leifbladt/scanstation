package scanstation.info.bladt.scanstation.model;

import java.util.Arrays;

public enum Key {
    C("C"),
    E_FLAT("Eb"),
    F("F"),
    B_FLAT("Bb");

    private final String displayName;

    Key(String displayName) {
        this.displayName = displayName;
    }

    public static Key parse(String key) {

        if (key == null || key.isBlank()) {
            return null;
        }

        return Arrays.stream(Key.values()).filter(k -> k.displayName.equals(key)).findFirst().orElse(null);
    }
    @Override
    public String toString() {
        return displayName;
    }
}

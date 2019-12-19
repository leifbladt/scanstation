package info.bladt.scanstation.model;

public enum Key {
    C("C"),
    E_FLAT("Eb"),
    B_FLAT("Bb");

    private final String displayName;

    private Key(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return displayName;
    }
}

package info.bladt.scanstation.model;

public enum Key {
    C("C"),
    E_FLAT("Eb"),
    B_FLAT("Bb");

    private final String displayName;

    private Key(String displayName) {
        this.displayName = displayName;
    }

    public static Key parse(String key) {
       switch (key) {
           case "C": return C;
           case "Eb": return E_FLAT;
           case "F": return F;
           case "Bb": return B_FLAT;
           default: return null;
       }
    }
    @Override
    public String toString() {
        return displayName;
    }
}

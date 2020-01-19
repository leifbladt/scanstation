package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Composition {

    public static final List<Composition> COMPOSITIONS = Collections.unmodifiableList(Arrays.asList(
            new Composition("March of the Trouts (Forellen-Marsch)"),
            new Composition("Flowerdale"),
            new Composition("Men Of Harlech"),
            new Composition("Share My Yoke"),
            new Composition("Bramwyn"),
            new Composition("Buster Strikes Back"),
            new Composition("Abide With Me"),
            new Composition("Anchors Aweigh"),
            new Composition("Concert Overture - Fingals Cave"),
            new Composition("13 Crimond"),
            new Composition("29 Eventide"),
            new Composition("Zum Geburtstag (Happy Birthday)"),
            new Composition("Abide With Me"),
            new Composition("A Moorside Suite"),
            new Composition("120 Hymns For Brass Band"),
            new Composition("Shepherd Hey")
    ));

    private final String name;

    public Composition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composition that = (Composition) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

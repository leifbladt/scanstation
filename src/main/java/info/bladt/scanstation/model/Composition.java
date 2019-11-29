package info.bladt.scanstation.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Composition {

    public static final List<Composition> COMPOSITIONS = Arrays.asList(
            new Composition("120 Hymns For Brass Band"),
            new Composition("Abide With Me"),
            new Composition("Shepherd Hey")
    );

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
package scanstation.info.bladt.scanstation.model;

import org.junit.jupiter.api.Test;
import scanstation.info.bladt.scanstation.model.Key;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    void parse_Null() {
        Key key = Key.parse(null);

        assertNull(key);
    }

    @Test
    void parse_EmptyString() {
        Key key = Key.parse("");

        assertNull(key);
    }

    @Test
    void parse_OnlyWhitespace() {
        Key key = Key.parse("  ");

        assertNull(key);
    }

    @Test
    void parse_B() {
        Key key = Key.parse("Bb");

        assertEquals(Key.B_FLAT, key);
    }

    @Test
    void parse_UnknownKey() {
        Key key = Key.parse("D");

        assertNull(key);
    }
}
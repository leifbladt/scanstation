package info.bladt.scanstation.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentTest {

    @Test
    void parse_NullValue() {
        Instrument instrument = Instrument.parse(null);

        assertNull(instrument);
    }

    @Test
    void parse_EmptyString() {
        Instrument instrument = Instrument.parse("");

        assertNull(instrument);
    }

    @Test
    void parse_OnlyWhitespace() {
        Instrument instrument = Instrument.parse("  ");

        assertNull(instrument);
    }

    @Test
    void parse_InstrumentWithoutKey() {
        Instrument instrument = Instrument.parse("Solo Cornet");

        assertEquals(new Instrument("Solo Cornet"), instrument);
    }

    @Test
    void parse_InstrumentWithKey() {
        Instrument instrument = Instrument.parse("Solo Cornet_Bb");

        assertEquals(new Instrument("Solo Cornet", Key.B_FLAT), instrument);
    }
    @Test
    void parse_InstrumentWithUnknownKey() {
        Instrument instrument = Instrument.parse("Solo Cornet_D");

        assertEquals(new Instrument("Solo Cornet"), instrument);
    }
}
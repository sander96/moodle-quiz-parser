package dev.rednas.moodle.question.common.input.dropdown;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class OptionTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Option.class).verify();
    }
}

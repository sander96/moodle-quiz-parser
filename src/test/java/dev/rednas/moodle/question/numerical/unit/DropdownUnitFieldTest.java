package dev.rednas.moodle.question.numerical.unit;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class DropdownUnitFieldTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(DropdownUnitField.class).verify();
    }
}

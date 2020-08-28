package dev.rednas.moodle.question.numerical.unit;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class SelectionControlUnitFieldTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(SelectionControlUnitField.class).verify();
    }
}

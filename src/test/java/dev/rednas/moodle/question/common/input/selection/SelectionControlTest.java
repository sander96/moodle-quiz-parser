package dev.rednas.moodle.question.common.input.selection;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class SelectionControlTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(SelectionControl.class).verify();
    }
}

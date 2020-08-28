package dev.rednas.moodle.question.common.input.dropdown;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class DropdownTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Dropdown.class).verify();
    }
}

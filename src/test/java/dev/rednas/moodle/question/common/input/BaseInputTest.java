package dev.rednas.moodle.question.common.input;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class BaseInputTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(BaseInput.class).verify();
    }
}

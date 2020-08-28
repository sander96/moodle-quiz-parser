package dev.rednas.moodle.question.common.input.text;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TextFieldTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TextField.class).verify();
    }
}

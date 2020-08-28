package dev.rednas.moodle.question.multianswer.subquestion;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class DropdownSubquestionTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(DropdownSubquestion.class).verify();
    }
}

package dev.rednas.moodle.question.multianswer.subquestion;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class MultichoiceSubquestionTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(MultichoiceSubquestion.class).verify();
    }
}

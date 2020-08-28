package dev.rednas.moodle.question.info;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class QuestionInfoTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(QuestionInfo.class).verify();
    }
}

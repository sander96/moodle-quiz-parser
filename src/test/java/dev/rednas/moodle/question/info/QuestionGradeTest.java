package dev.rednas.moodle.question.info;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class QuestionGradeTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(QuestionGrade.class).verify();
    }
}

package dev.rednas.moodle.quiz;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class QuizTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Quiz.class).verify();
    }
}

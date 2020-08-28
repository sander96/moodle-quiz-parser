package dev.rednas.moodle.question.info;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Outcome.class).verify();
    }
}

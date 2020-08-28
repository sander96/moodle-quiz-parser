package dev.rednas.moodle.question.description;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptionQuestionTest {

    @Test
    void parse() {
        String html = TestUtils.readHtml("description", "description.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());
        DescriptionQuestion description = (DescriptionQuestion) quiz.getQuestions().get(0);
        assertEquals("This is description...", description.getQuestionText());
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(DescriptionQuestion.class).verify();
    }
}

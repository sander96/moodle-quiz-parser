package dev.rednas.moodle.question.calculated;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CalculatedQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("calculated", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        CalculatedQuestion calculated = (CalculatedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(calculated, "0.00", "1.00", 1L, "incorrect");
        assertEquals("Calculate the area of a rectangle. 4.7 is the base and 7.7 is the height.",
                calculated.getQuestionText());
        assertEquals("q34:1_answer", calculated.getTextField().getInput().getId());
        assertEquals("Answer:", calculated.getTextField().getText());
        assertEquals("33", calculated.getTextField().getInput().getValue());
        assertNull(calculated.getUnitChoice());
    }
}

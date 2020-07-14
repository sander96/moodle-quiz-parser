package dev.rednas.moodle.question.calculatedsimple;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CalculatedSimpleQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("calculatedsimple", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        CalculatedSimpleQuestion calculatedsimple = (CalculatedSimpleQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(calculatedsimple, "0.00", "1.00", 1L, "incorrect");
        assertEquals("Abc 1.2m * 4.4m is ...", calculatedsimple.getQuestionText());
        assertEquals("q25:1_answer", calculatedsimple.getTextField().getInput().getId());
        assertEquals("Answer:", calculatedsimple.getTextField().getText());
        assertEquals("5", calculatedsimple.getTextField().getInput().getValue());
        assertNull(calculatedsimple.getUnitChoice().getDropdown());
        assertEquals(3, calculatedsimple.getUnitChoice().getRadioButtons().size());
    }
}

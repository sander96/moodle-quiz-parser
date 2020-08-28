package dev.rednas.moodle.question.calculatedsimple;

import dev.rednas.moodle.question.numerical.unit.SelectionControlUnitField;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatedSimpleQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("calculatedsimple", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        CalculatedSimpleQuestion calculatedSimple = (CalculatedSimpleQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(calculatedSimple, 0.0f, 1.0f, 1L, "incorrect");
        assertEquals("Abc 1.2m * 4.4m is ...", calculatedSimple.getQuestionText());
        assertEquals("q25:1_answer", calculatedSimple.getTextField().getInput().getId());
        assertEquals("Answer:", calculatedSimple.getTextField().getText());
        assertEquals("5", calculatedSimple.getTextField().getInput().getValue());
        assertEquals(3, ((SelectionControlUnitField) calculatedSimple.getUnitChoice()).getValue().size());
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(CalculatedSimpleQuestion.class).verify();
    }
}

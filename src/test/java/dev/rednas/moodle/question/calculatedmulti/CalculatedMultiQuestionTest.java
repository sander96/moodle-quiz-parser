package dev.rednas.moodle.question.calculatedmulti;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import dev.rednas.moodle.question.info.GradeState;
import dev.rednas.moodle.question.multichoice.MultichoiceQuestion;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatedMultiQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("calculatedmulti", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, -0.83f, 1.0f, 1L, "incorrect");

        MultichoiceQuestion multichoiceQuestion = (MultichoiceQuestion) question;
        assertEquals("3.6m * 3.3m is ...", multichoiceQuestion.getQuestionText());
        assertEquals(3, multichoiceQuestion.getSelections().size());

        InputWithText<SelectionControl> selection1 = multichoiceQuestion.getSelections().get(0);
        assertEquals("a. 3.6 * 3.3", selection1.getText());
        assertNull(selection1.getFeedback());
        assertFalse(selection1.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection1.getInput().getSelectionType());
        assertNull(selection1.getInput().getGradeState());

        InputWithText<SelectionControl> selection2 = multichoiceQuestion.getSelections().get(1);
        assertEquals("b. 444444", selection2.getText());
        assertEquals(":(", selection2.getFeedback());
        assertTrue(selection2.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection2.getInput().getSelectionType());
        assertEquals(GradeState.INCORRECT, selection2.getInput().getGradeState());
        assertEquals("incorrect", GradeState.INCORRECT.getCssClass());

        InputWithText<SelectionControl> selection3 = multichoiceQuestion.getSelections().get(2);
        assertEquals("c. 3.6", selection3.getText());
        assertNull(selection1.getFeedback());
        assertFalse(selection3.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection3.getInput().getSelectionType());
        assertNull(selection1.getInput().getGradeState());
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(CalculatedMultiQuestion.class).verify();
    }
}

package dev.rednas.moodle.question.calculatedmulti;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.GradeState;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import dev.rednas.moodle.question.multichoice.Multichoice;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatedMultiQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("calculatedmulti", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "-0.83", "1.00", 1L, "incorrect");

        Multichoice multichoice = (Multichoice) question;
        assertEquals("3.6m * 3.3m is ...", multichoice.getQuestionText());
        assertEquals(3, multichoice.getSelections().size());

        InputWithText<SelectionControl> selection1 = multichoice.getSelections().get(0);
        assertEquals("a. 3.6 * 3.3", selection1.getText());
        assertNull(selection1.getFeedback());
        assertFalse(selection1.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection1.getInput().getSelectionType());
        assertNull(selection1.getInput().getGradeState());

        InputWithText<SelectionControl> selection2 = multichoice.getSelections().get(1);
        assertEquals("b. 444444", selection2.getText());
        assertEquals(":(", selection2.getFeedback());
        assertTrue(selection2.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection2.getInput().getSelectionType());
        assertEquals(GradeState.INCORRECT, selection2.getInput().getGradeState());

        InputWithText<SelectionControl> selection3 = multichoice.getSelections().get(2);
        assertEquals("c. 3.6", selection3.getText());
        assertNull(selection1.getFeedback());
        assertFalse(selection3.getInput().isSelected());
        assertEquals(SelectionType.RADIO, selection3.getInput().getSelectionType());
        assertNull(selection1.getInput().getGradeState());
    }
}

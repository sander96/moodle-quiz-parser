package dev.rednas.moodle.question.multichoice;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.question.info.GradeState;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import dev.rednas.moodle.question.info.Outcome;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultichoiceQuestionTest {

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("multichoice", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, 0.0f, 1.0f, 1L, "incorrect");

        MultichoiceQuestion multichoiceQuestion = (MultichoiceQuestion) question;
        assertEquals("Which of these are fruits", multichoiceQuestion.getQuestionText());
        assertEquals(3, multichoiceQuestion.getSelections().size());

        InputWithText<SelectionControl> selection1 = multichoiceQuestion.getSelections().get(0);
        assertEquals("a. Car", selection1.getText());
        assertEquals("Wrong...", selection1.getFeedback());
        assertTrue(selection1.getInput().isSelected());
        assertEquals(SelectionType.CHECKBOX, selection1.getInput().getSelectionType());
        assertEquals(GradeState.INCORRECT, selection1.getInput().getGradeState());

        InputWithText<SelectionControl> selection2 = multichoiceQuestion.getSelections().get(1);
        assertEquals("b. Orange", selection2.getText());
        assertNull(selection2.getFeedback());
        assertTrue(selection2.getInput().isSelected());
        assertEquals(SelectionType.CHECKBOX, selection2.getInput().getSelectionType());
        assertEquals(GradeState.CORRECT, selection2.getInput().getGradeState());

        InputWithText<SelectionControl> selection3 = multichoiceQuestion.getSelections().get(2);
        assertEquals("c. Apple", selection3.getText());
        assertEquals("This one is correct", selection3.getFeedback());
        assertTrue(selection3.getInput().isSelected());
        assertEquals(SelectionType.CHECKBOX, selection3.getInput().getSelectionType());
        assertEquals(GradeState.CORRECT, selection3.getInput().getGradeState());

        Outcome outcome = multichoiceQuestion.getOutcome();
        assertEquals("Your answer is incorrect.", outcome.getSpecificFeedback());
        assertEquals("General feedback...", outcome.getGeneralFeedback());
        assertEquals("The correct answers are: Apple, Orange", outcome.getRightAnswer());
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(MultichoiceQuestion.class).verify();
    }
}

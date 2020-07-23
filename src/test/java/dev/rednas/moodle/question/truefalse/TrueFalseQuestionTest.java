package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.info.GradeState;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.quiz.Quiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("truefalse", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, null, 1.0f, 1L, "notyetanswered");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getPrompt());

        InputWithText<SelectionControl> radioButtonWithTextTrue = truefalseQuestion.getTrueRadioButton();
        assertEquals("q3:1_answertrue", radioButtonWithTextTrue.getInput().getId());
        assertEquals("True", radioButtonWithTextTrue.getText());
        assertFalse(radioButtonWithTextTrue.getInput().isSelected());
        assertNull(radioButtonWithTextTrue.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextTrue.getInput().getSelectionType());

        InputWithText<SelectionControl> radioButtonWithTextFalse = truefalseQuestion.getFalseRadioButton();
        assertEquals("q3:1_answerfalse", radioButtonWithTextFalse.getInput().getId());
        assertEquals("False", radioButtonWithTextFalse.getText());
        assertFalse(radioButtonWithTextFalse.getInput().isSelected());
        assertNull(radioButtonWithTextTrue.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextFalse.getInput().getSelectionType());
    }

    @Test
    void parseWrong() {
        String html = TestUtils.readHtml("truefalse", "wrong1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, 0.0f, 1.0f, 1L, "incorrect");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getPrompt());

        InputWithText<SelectionControl> radioButtonWithTextTrue = truefalseQuestion.getTrueRadioButton();
        assertEquals("q3:1_answertrue", radioButtonWithTextTrue.getInput().getId());
        assertEquals("True", radioButtonWithTextTrue.getText());
        assertFalse(radioButtonWithTextTrue.getInput().isSelected());
        assertNull(radioButtonWithTextTrue.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextTrue.getInput().getSelectionType());

        InputWithText<SelectionControl> radioButtonWithTextFalse = truefalseQuestion.getFalseRadioButton();
        assertEquals("q3:1_answerfalse", radioButtonWithTextFalse.getInput().getId());
        assertEquals("False", radioButtonWithTextFalse.getText());
        assertTrue(radioButtonWithTextFalse.getInput().isSelected());
        assertEquals(GradeState.INCORRECT, radioButtonWithTextFalse.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextFalse.getInput().getSelectionType());
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("truefalse", "correct1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, 1.0f, 1.0f, 1L, "correct");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getPrompt());

        InputWithText<SelectionControl> radioButtonWithTextTrue = truefalseQuestion.getTrueRadioButton();
        assertEquals("q4:1_answertrue", radioButtonWithTextTrue.getInput().getId());
        assertEquals("True", radioButtonWithTextTrue.getText());
        assertTrue(radioButtonWithTextTrue.getInput().isSelected());
        assertEquals(GradeState.CORRECT, radioButtonWithTextTrue.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextTrue.getInput().getSelectionType());

        InputWithText<SelectionControl> radioButtonWithTextFalse = truefalseQuestion.getFalseRadioButton();
        assertEquals("q4:1_answerfalse", radioButtonWithTextFalse.getInput().getId());
        assertEquals("False", radioButtonWithTextFalse.getText());
        assertFalse(radioButtonWithTextFalse.getInput().isSelected());
        assertNull(radioButtonWithTextFalse.getInput().getGradeState());
        assertEquals(SelectionType.RADIO, radioButtonWithTextFalse.getInput().getSelectionType());
    }
}

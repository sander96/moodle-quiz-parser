package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.selection.RadioButton;
import dev.rednas.moodle.quiz.Quiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("truefalse", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, null, "1.00", 1L, "notyetanswered");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getAnswerBlock().getPrompt());

        RadioButton radioButtonTrue = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonTrue();
        assertEquals("q3:1_answertrue", radioButtonTrue.getId());
        assertEquals("True", radioButtonTrue.getLabel());
        assertFalse(radioButtonTrue.isSelected());
        assertNull(radioButtonTrue.getCorrect());

        RadioButton radioButtonFalse = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonFalse();
        assertEquals("q3:1_answerfalse", radioButtonFalse.getId());
        assertEquals("False", radioButtonFalse.getLabel());
        assertFalse(radioButtonFalse.isSelected());
        assertNull(radioButtonFalse.getCorrect());
    }

    @Test
    void parseWrong() {
        String html = TestUtils.readHtml("truefalse", "wrong1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.00", "1.00", 1L, "incorrect");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getAnswerBlock().getPrompt());

        RadioButton radioButtonTrue = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonTrue();
        assertEquals("q3:1_answertrue", radioButtonTrue.getId());
        assertEquals("True", radioButtonTrue.getLabel());
        assertFalse(radioButtonTrue.isSelected());
        assertNull(radioButtonTrue.getCorrect());

        RadioButton radioButtonFalse = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonFalse();
        assertEquals("q3:1_answerfalse", radioButtonFalse.getId());
        assertEquals("False", radioButtonFalse.getLabel());
        assertTrue(radioButtonFalse.isSelected());
        assertFalse(radioButtonFalse.getCorrect());
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("truefalse", "correct1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "1.00", "1.00", 1L, "correct");

        TrueFalseQuestion truefalseQuestion = (TrueFalseQuestion) question;
        assertEquals("5 + 5 = 10", truefalseQuestion.getQuestionText());
        assertEquals("Select one:", truefalseQuestion.getAnswerBlock().getPrompt());

        RadioButton radioButtonTrue = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonTrue();
        assertEquals("q4:1_answertrue", radioButtonTrue.getId());
        assertEquals("True", radioButtonTrue.getLabel());
        assertTrue(radioButtonTrue.isSelected());
        assertEquals(true, radioButtonTrue.getCorrect());

        RadioButton radioButtonFalse = truefalseQuestion.getAnswerBlock().getAnswer().getRadioButtonFalse();
        assertEquals("q4:1_answerfalse", radioButtonFalse.getId());
        assertEquals("False", radioButtonFalse.getLabel());
        assertFalse(radioButtonFalse.isSelected());
        assertNull(radioButtonFalse.getCorrect());
    }
}

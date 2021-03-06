package dev.rednas.moodle.question.shortanswer;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortanswerQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("shortanswer", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, null, 1.0f, 1L, "notyetanswered");

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q8:1_answer", shortanswer.getTextField().getInput().getId());
        assertEquals("Answer:", shortanswer.getTextField().getText());
        assertEquals("", shortanswer.getTextField().getInput().getValue());
    }

    @Test
    void parseWrong() {
        String html = TestUtils.readHtml("shortanswer", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, 0.0f, 1.0f, 1L, "incorrect");

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q8:1_answer", shortanswer.getTextField().getInput().getId());
        assertEquals("Answer:", shortanswer.getTextField().getText());
        assertEquals("adadsa", shortanswer.getTextField().getInput().getValue());
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("shortanswer", "correct1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, 1.0f, 1.0f, 1L, "correct");

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q9:1_answer", shortanswer.getTextField().getInput().getId());
        assertEquals("Answer:", shortanswer.getTextField().getText());
        assertEquals("green", shortanswer.getTextField().getInput().getValue());
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ShortanswerQuestion.class).verify();
    }
}

package dev.rednas.moodle.question.type.shortanswer;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortanswerQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("shortanswer", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, null, "1.00", 1L, "notyetanswered", QuestionType.SHORTANSWER);

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q8:1_answer", shortanswer.getTextField().getId());
        assertEquals("Answer:", shortanswer.getTextField().getLabel());
        assertEquals("", shortanswer.getTextField().getValue());
    }

    @Test
    void parseWrong() {
        String html = TestUtils.readHtml("shortanswer", "incorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.00", "1.00", 1L, "incorrect", QuestionType.SHORTANSWER);

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q8:1_answer", shortanswer.getTextField().getId());
        assertEquals("Answer:", shortanswer.getTextField().getLabel());
        assertEquals("adadsa", shortanswer.getTextField().getValue());
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("shortanswer", "correct1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "1.00", "1.00", 1L, "correct", QuestionType.SHORTANSWER);

        ShortanswerQuestion shortanswer = (ShortanswerQuestion) question;
        assertEquals("What color is an apple?", shortanswer.getQuestionText());
        assertEquals("q9:1_answer", shortanswer.getTextField().getId());
        assertEquals("Answer:", shortanswer.getTextField().getLabel());
        assertEquals("green", shortanswer.getTextField().getValue());
    }
}

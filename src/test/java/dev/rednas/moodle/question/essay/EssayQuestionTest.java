package dev.rednas.moodle.question.essay;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EssayQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("essay", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(3, quiz.getQuestions().size());

        GradedQuestion question1 = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question1, null, 1.0f, 1L, "notyetanswered");
        EssayQuestion essay1 = (EssayQuestion) question1;
        assertEquals("This is essay question text...", essay1.getQuestionText());
        assertEquals("<p>Response...<br></p>", essay1.getTextArea().getValue());
        assertNull(essay1.getFiles());

        GradedQuestion question3 = (GradedQuestion) quiz.getQuestions().get(2);
        AssertionUtils.assertQuestionInfo(question3, null, 1.0f, 3L, "notyetanswered");
        EssayQuestion essay3 = (EssayQuestion) question3;
        assertEquals("This is essay question text...", essay3.getQuestionText());
        assertNull(essay3.getTextArea().getValue());
        assertNull(essay1.getFiles());
    }

    @Test
    void parseGraded() {
        String html = TestUtils.readHtml("essay", "graded1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(3, quiz.getQuestions().size());

        GradedQuestion question1 = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question1, 1.0f, 1.0f, 1L, "complete");
        EssayQuestion essay1 = (EssayQuestion) question1;
        assertEquals("This is essay question text...", essay1.getQuestionText());
        assertEquals("Test test", essay1.getTextArea().getValue());
        assertNull(essay1.getFiles());

        GradedQuestion question3 = (GradedQuestion) quiz.getQuestions().get(2);
        AssertionUtils.assertQuestionInfo(question3, 1.0f, 1.0f, 3L, "complete");
        EssayQuestion essay3 = (EssayQuestion) question3;
        assertEquals("This is essay question text...", essay3.getQuestionText());
        assertNull(essay3.getTextArea().getValue());
        assertEquals(1, essay3.getFiles().size());
    }
}

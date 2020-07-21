package dev.rednas.moodle.parser;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.util.TestUtils;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.quiz.Quiz;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class QuizParserTest {

    @Test
    void parse() {
        String html = TestUtils.readHtml("full", "full1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"en", "et"})
    void parseLocalisationStrings(String language) {
        String html = TestUtils.readHtml("language", language + "1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        assertEquals(1L, question.getInfo().getNumber());
        assertEquals("correct", question.getInfo().getState());
        assertEquals("1.00", question.getInfo().getGrade().getMax());
        assertEquals("1.00", question.getInfo().getGrade().getMark());
    }

    @Test
    void parserSkipsErrors() {
        String html = TestUtils.readHtml("unknown", "unknowntype1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());
        assertNull(quiz.getQuestions().get(0));
    }
}

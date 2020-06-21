package dev.rednas.moodle.parser;

import dev.rednas.moodle.TestUtils;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.quiz.Quiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizParserTest {

    @Test
    void parse() {
        String html = TestUtils.readHtml("full1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        assertEquals(QuestionType.TRUEFALSE, question.getQuestionType());
    }
}

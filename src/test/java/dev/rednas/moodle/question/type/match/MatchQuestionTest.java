package dev.rednas.moodle.question.type.match;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.question.type.common.input.dropdown.Dropdown;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("match", "notyetanswered1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, null, "1.00",
                1L, "notyetanswered", QuestionType.MATCH);

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "purple", "black", "green", "pink", "orange");

        Dropdown dropdown1 = matchQuestion.getDropdowns().get(0);
        assertEquals("Orange is mostly", dropdown1.getText());
        assertNull(dropdown1.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown1, 0,
                options);

        Dropdown dropdown2 = matchQuestion.getDropdowns().get(1);
        assertEquals("Apple is", dropdown2.getText());
        assertNull(dropdown2.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown2, 0,
                options);
    }

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("match", "incorrect2.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.00", "1.00",
                1L, "incorrect", QuestionType.MATCH);

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "pink", "purple", "black", "green", "orange");

        Dropdown dropdown1 = matchQuestion.getDropdowns().get(0);
        assertEquals("Apple is", dropdown1.getText());
        assertFalse(dropdown1.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown1, 1,
                options);

        Dropdown dropdown2 = matchQuestion.getDropdowns().get(1);
        assertEquals("Orange is mostly", dropdown2.getText());
        assertFalse(dropdown2.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown2, 1,
                options);
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("match", "correct3.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "1.00", "1.00",
                1L, "correct", QuestionType.MATCH);

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "black", "orange", "green", "pink", "purple");

        Dropdown dropdown1 = matchQuestion.getDropdowns().get(0);
        assertEquals("Orange is mostly", dropdown1.getText());
        assertTrue(dropdown1.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown1, 2,
                options);

        Dropdown dropdown2 = matchQuestion.getDropdowns().get(1);
        assertEquals("Apple is", dropdown2.getText());
        assertTrue(dropdown2.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown2, 3,
                options);
    }

    @Test
    void parsePartiallyCorrect() {
        String html = TestUtils.readHtml("match", "partiallycorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.50", "1.00",
                1L, "partiallycorrect", QuestionType.MATCH);

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "purple", "black", "green", "pink", "orange");

        Dropdown dropdown1 = matchQuestion.getDropdowns().get(0);
        assertEquals("Orange is mostly", dropdown1.getText());
        assertTrue(dropdown1.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown1, 5,
                options);

        Dropdown dropdown2 = matchQuestion.getDropdowns().get(1);
        assertEquals("Apple is", dropdown2.getText());
        assertFalse(dropdown2.getCorrect());
        AssertionUtils.assertDropdownOptions(dropdown2, 4,
                options);
    }

}

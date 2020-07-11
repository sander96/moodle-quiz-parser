package dev.rednas.moodle.question.match;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.GradeState;
import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
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
        AssertionUtils.assertQuestionInfo(question, null, "1.00", 1L, "notyetanswered");

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "purple", "black", "green", "pink", "orange");

        InputWithText<Dropdown> dropdownInputWithText1 = matchQuestion.getDropdowns().get(0);
        Dropdown dropdown1 = dropdownInputWithText1.getInput();
        assertEquals("Orange is mostly", dropdownInputWithText1.getText());
        assertNull(dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown1, 0,
                options);

        InputWithText<Dropdown> dropdownInputWithText2 = matchQuestion.getDropdowns().get(1);
        Dropdown dropdown2 = dropdownInputWithText2.getInput();
        assertEquals("Apple is", dropdownInputWithText2.getText());
        assertNull(dropdown2.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown2, 0,
                options);
    }

    @Test
    void parseIncorrect() {
        String html = TestUtils.readHtml("match", "incorrect2.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.00", "1.00", 1L, "incorrect");

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "pink", "purple", "black", "green", "orange");

        InputWithText<Dropdown> dropdownInputWithText1 = matchQuestion.getDropdowns().get(0);
        Dropdown dropdown1 = dropdownInputWithText1.getInput();
        assertEquals("Apple is", dropdownInputWithText1.getText());
        assertEquals(GradeState.INCORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown1, 1,
                options);

        InputWithText<Dropdown> dropdownInputWithText2 = matchQuestion.getDropdowns().get(1);
        Dropdown dropdown2 = dropdownInputWithText2.getInput();
        assertEquals("Orange is mostly", dropdownInputWithText2.getText());
        assertEquals(GradeState.INCORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown2, 1,
                options);
    }

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("match", "correct3.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "1.00", "1.00", 1L, "correct");

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "black", "orange", "green", "pink", "purple");

        InputWithText<Dropdown> dropdownInputWithText1 = matchQuestion.getDropdowns().get(0);
        Dropdown dropdown1 = dropdownInputWithText1.getInput();
        assertEquals("Orange is mostly", dropdownInputWithText1.getText());
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown1, 2, options);

        InputWithText<Dropdown> dropdownInputWithText2 = matchQuestion.getDropdowns().get(1);
        Dropdown dropdown2 = dropdownInputWithText2.getInput();
        assertEquals("Apple is", dropdownInputWithText2.getText());
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown2, 3, options);
    }

    @Test
    void parsePartiallyCorrect() {
        String html = TestUtils.readHtml("match", "partiallycorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        Question question = quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.50", "1.00", 1L, "partiallycorrect");

        MatchQuestion matchQuestion = (MatchQuestion) question;
        assertEquals("This question is to test your fruit color knowledge.", matchQuestion.getQuestionText());
        assertEquals(2, matchQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "purple", "black", "green", "pink", "orange");

        InputWithText<Dropdown> dropdownInputWithText1 = matchQuestion.getDropdowns().get(0);
        Dropdown dropdown1 = dropdownInputWithText1.getInput();
        assertEquals("Orange is mostly", dropdownInputWithText1.getText());
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown1, 5,
                options);

        InputWithText<Dropdown> dropdownInputWithText2 = matchQuestion.getDropdowns().get(1);
        Dropdown dropdown2 = dropdownInputWithText2.getInput();
        assertEquals("Apple is", dropdownInputWithText2.getText());
        assertEquals(GradeState.INCORRECT, dropdown2.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown2, 4,
                options);
    }

}

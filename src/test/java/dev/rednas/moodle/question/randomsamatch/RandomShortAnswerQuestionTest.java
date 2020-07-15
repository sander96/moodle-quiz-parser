package dev.rednas.moodle.question.randomsamatch;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.GradeState;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomShortAnswerQuestionTest {

    @Test
    void parseCorrect() {
        String html = TestUtils.readHtml("randomsamatch", "correct1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "1.00", "1.00", 1L, "correct");

        RandomShortAnswerQuestion randomQuestion = (RandomShortAnswerQuestion) question;
        assertEquals("Question text...", randomQuestion.getQuestionText());
        assertEquals(2, randomQuestion.getDropdowns().size());
        List<String> options = List.of("Choose...", "yes", "green");

        InputWithText<Dropdown> dropdownInputWithText1 = randomQuestion.getDropdowns().get(0);
        Dropdown dropdown1 = dropdownInputWithText1.getInput();
        assertEquals("Is apple a fruit?", dropdownInputWithText1.getText());
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown1, 1, options);

        InputWithText<Dropdown> dropdownInputWithText2 = randomQuestion.getDropdowns().get(1);
        Dropdown dropdown2 = dropdownInputWithText2.getInput();
        assertEquals("What color is an apple?", dropdownInputWithText2.getText());
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
        AssertionUtils.assertDropdownOptions(dropdown2, 2, options);
    }
}

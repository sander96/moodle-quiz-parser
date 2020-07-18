package dev.rednas.moodle.question.gapselect;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.GradeState;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GapselectQuestionTest {

    @Test
    void parsePartiallyCorrect() {
        String html = TestUtils.readHtml("gapselect", "partiallycorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "0.80", "1.00", 1L, "partiallycorrect");

        GapselectQuestion gapselectQuestion = (GapselectQuestion) question;
        String questionText = "The largest number is [[0]] then followed by [[1]]. The smallest is [[2]]. [[3]] comes before [[4]].";
        assertEquals(questionText, gapselectQuestion.getQuestionText());

        assertEquals(5, gapselectQuestion.getDropdowns().size());
        Dropdown dropdown1 = gapselectQuestion.getDropdowns().get(0);
        List<String> options = List.of("", "300", "200", "100");
        AssertionUtils.assertDropdownOptions(dropdown1, 1, options);
        assertEquals(GradeState.CORRECT, dropdown1.getGradeState());
    }
}

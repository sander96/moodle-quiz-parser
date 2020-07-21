package dev.rednas.moodle.question.multianswer;

import dev.rednas.moodle.quiz.QuizParser;
import dev.rednas.moodle.question.info.GradeState;
import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.multianswer.subquestion.DropdownSubquestion;
import dev.rednas.moodle.question.multianswer.subquestion.MultichoiceSubquestion;
import dev.rednas.moodle.question.multianswer.subquestion.TextFieldSubquestion;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiAnswerQuestionTest {

    @Test
    void parsePartiallyCorrect() {
        String html = TestUtils.readHtml("multianswer", "partiallycorrect1.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(1, quiz.getQuestions().size());

        GradedQuestion question = (GradedQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(question, "2.22", "5.00", 1L, "partiallycorrect");
        MultiAnswerQuestion multiAnswerQuestion = (MultiAnswerQuestion) question;

        String resource = TestUtils.getResource("data", "multianswer", "multianswer1.txt");
        assertEquals(resource, multiAnswerQuestion.getQuestionText());
        assertEquals(13, multiAnswerQuestion.getSubquestions().size());

        DropdownSubquestion subquestion1 = (DropdownSubquestion) multiAnswerQuestion.getSubquestions().get(0);
        AssertionUtils.assertDropdownOptions(subquestion1.getValue(), 1, List.of("", "California", "Arizona"));
        assertEquals("Correct OK The correct answer is: California Mark 1.00 out of 1.00", subquestion1.getFeedback());

        TextFieldSubquestion subquestion5 = (TextFieldSubquestion) multiAnswerQuestion.getSubquestions().get(4);
        assertEquals("Paris", subquestion5.getValue().getValue());
        assertEquals("Correct Congratulations! The correct answer is: Paris Mark 1.00 out of 1.00", subquestion5.getFeedback());

        MultichoiceSubquestion subquestion9 = (MultichoiceSubquestion) multiAnswerQuestion.getSubquestions().get(8);
        assertEquals(4, subquestion9.getValue().size());
        assertEquals("4. Answer that gives half the credit", subquestion9.getValue().get(3).getText());
        assertEquals(GradeState.PARTIALLY_CORRECT, subquestion9.getValue().get(3).getInput().getGradeState());
        assertTrue(subquestion9.getValue().get(3).getInput().isSelected());
        assertEquals("Mark 1.00 out of 2.00 The correct answer is: 3. Correct answer", subquestion9.getFeedback());
    }
}

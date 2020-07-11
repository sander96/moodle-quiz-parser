package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.parser.QuizParser;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.quiz.Quiz;
import dev.rednas.moodle.util.AssertionUtils;
import dev.rednas.moodle.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumericalQuestionTest {

    @Test
    void parseNotYetAnswered() {
        String html = TestUtils.readHtml("numerical", "partiallycorrect2.html");
        Quiz quiz = QuizParser.parse(html);
        assertEquals(3, quiz.getQuestions().size());

        NumericalQuestion numerical1 = (NumericalQuestion) quiz.getQuestions().get(0);
        AssertionUtils.assertQuestionInfo(numerical1, "0.90", "1.00", 1L, "partiallycorrect");
        assertEquals("0.5m + 0.5m is equal to", numerical1.getQuestionText());
        assertEquals("q27:1_answer", numerical1.getTextField().getInput().getId());
        assertEquals("Answer:", numerical1.getTextField().getText());
        assertEquals("1", numerical1.getTextField().getInput().getValue());
        assertNull(numerical1.getUnitChoice().getDropdown());
        assertNull(numerical1.getUnitChoice().getRadioButtons());

        NumericalQuestion numerical2 = (NumericalQuestion) quiz.getQuestions().get(1);
        AssertionUtils.assertQuestionInfo(numerical2, "0.90", "1.00", 2L, "partiallycorrect");
        assertEquals("0.5m + 0.5m is equal to", numerical2.getQuestionText());
        assertEquals("q27:2_answer", numerical2.getTextField().getInput().getId());
        assertEquals("Answer:", numerical2.getTextField().getText());
        assertEquals("1", numerical2.getTextField().getInput().getValue());
        assertNull(numerical2.getUnitChoice().getRadioButtons());
        Dropdown dropdown = numerical2.getUnitChoice().getDropdown();
        assertEquals(4, dropdown.getOptions().size());
        assertTrue(dropdown.getOptions().get(2).isSelected());
        List<String> options = List.of("Choose...", "m", "km", "cm");
        AssertionUtils.assertDropdownOptions(dropdown, 2, options);

        NumericalQuestion numerical3 = (NumericalQuestion) quiz.getQuestions().get(2);
        AssertionUtils.assertQuestionInfo(numerical3, "0.90", "1.00", 3L, "partiallycorrect");
        assertEquals("0.5m + 0.5m is equal to", numerical3.getQuestionText());
        assertEquals("q27:3_answer", numerical3.getTextField().getInput().getId());
        assertEquals("Answer:", numerical3.getTextField().getText());
        assertEquals("1", numerical3.getTextField().getInput().getValue());
        assertNull(numerical3.getUnitChoice().getDropdown());
        List<SelectionControl> radioButtons = numerical3.getUnitChoice().getRadioButtons();
        assertEquals(3, radioButtons.size());
        assertTrue(radioButtons.get(2).isSelected());
    }
}

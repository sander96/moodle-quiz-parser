package dev.rednas.moodle.util;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.dropdown.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssertionUtils {

    public static void assertQuestionInfo(GradedQuestion question, Float mark, Float max,
                                          Long number, String state) {
        assertEquals(mark, question.getInfo().getGrade().getMark());
        assertEquals(max, question.getInfo().getGrade().getMax());
        assertEquals(number, question.getInfo().getNumber());
        assertEquals(state, question.getInfo().getState());
    }

    public static void assertOption(Option option, String text, boolean selected) {
        assertEquals(text, option.getText());
        assertEquals(selected, option.isSelected());
    }

    public static void assertDropdownOptions(Dropdown dropdown, int selectedIndex, List<String> optionStrings) {
        List<Option> options = dropdown.getOptions();
        for (int i = 0; i < options.size(); i++) {
            AssertionUtils.assertOption(options.get(i), optionStrings.get(i), i == selectedIndex);
        }
    }
}

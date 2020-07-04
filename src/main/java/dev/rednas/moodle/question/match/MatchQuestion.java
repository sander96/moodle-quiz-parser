package dev.rednas.moodle.question.match;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class MatchQuestion extends Question {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.ablock", converter = DropdownConverter.class)
    private List<InputWithText<Dropdown>> dropdowns;
}

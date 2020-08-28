package dev.rednas.moodle.question.gapselect;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class GapselectQuestion extends GradedQuestion {

    @Selector(value = "div.qtext", converter = QuestionTextConverter.class)
    private String questionText;

    @Selector(value = "div.qtext", converter = DropdownConverter.class)
    private List<Dropdown> dropdowns;
}

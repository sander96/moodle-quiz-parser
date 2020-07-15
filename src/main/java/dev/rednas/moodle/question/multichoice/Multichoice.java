package dev.rednas.moodle.question.multichoice;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Multichoice extends GradedQuestion {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.answer", converter = SelectionControlConverter.class)
    private List<InputWithText<SelectionControl>> selections;
}

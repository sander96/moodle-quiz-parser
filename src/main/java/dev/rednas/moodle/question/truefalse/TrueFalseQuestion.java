package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class TrueFalseQuestion extends GradedQuestion {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.answer > div.r0", converter = SelectionControlWithTextConverter.class)
    private InputWithText<SelectionControl> trueRadioButton;

    @Selector(value = "div.answer > div.r1", converter = SelectionControlWithTextConverter.class)
    private InputWithText<SelectionControl> falseRadioButton;
}

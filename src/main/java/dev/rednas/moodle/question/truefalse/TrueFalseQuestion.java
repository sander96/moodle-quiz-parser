package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.RadioButton;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class TrueFalseQuestion extends Question {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.prompt")
    private String prompt;

    @Selector(value = "div.answer > div.r0", converter = RadioButtonConverter.class)
    private InputWithText<RadioButton> trueRadioButton;

    @Selector(value = "div.answer > div.r1", converter = RadioButtonConverter.class)
    private InputWithText<RadioButton> falseRadioButton;
}

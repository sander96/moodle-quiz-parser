package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.text.TextField;
import dev.rednas.moodle.question.shortanswer.TextFieldConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class NumericalQuestion extends Question {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.ablock", converter = TextFieldConverter.class)
    private InputWithText<TextField> textField;

    @Selector(value = "div.formulation")
    private UnitField unitChoice;
}

package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.common.input.selection.RadioButton;
import lombok.Data;

@Data
public class Answer {
    private RadioButton radioButtonTrue;
    private RadioButton radioButtonFalse;
}

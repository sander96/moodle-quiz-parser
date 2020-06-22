package dev.rednas.moodle.question.type.truefalse;

import dev.rednas.moodle.question.type.common.input.selection.RadioButton;
import lombok.Data;

@Data
public class Answer {
    private RadioButton radioButtonTrue;
    private RadioButton radioButtonFalse;
}

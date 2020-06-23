package dev.rednas.moodle.question.type.common.input.text;

import dev.rednas.moodle.question.type.common.input.BaseInput;
import dev.rednas.moodle.question.type.common.input.InputType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextField extends BaseInput {
    private String label;
    private String value;
    private Boolean correct;    // TODO this can be partially correct?

    public TextField() {
        super(InputType.TEXT);
    }
}

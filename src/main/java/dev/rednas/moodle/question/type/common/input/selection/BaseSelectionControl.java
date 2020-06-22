package dev.rednas.moodle.question.type.common.input.selection;

import dev.rednas.moodle.question.type.common.input.BaseInput;
import dev.rednas.moodle.question.type.common.input.InputType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseSelectionControl extends BaseInput {
    private String label;
    private boolean selected;

    public BaseSelectionControl(InputType type) {
        super(type);
    }
}

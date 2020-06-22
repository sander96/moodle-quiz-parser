package dev.rednas.moodle.question.type.common.input.selection;

import dev.rednas.moodle.question.type.common.input.InputType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RadioButton extends BaseSelectionControl {
    public RadioButton() {
        super(InputType.RADIO);
    }
}

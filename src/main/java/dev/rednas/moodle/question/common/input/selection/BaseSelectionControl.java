package dev.rednas.moodle.question.common.input.selection;

import dev.rednas.moodle.question.common.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSelectionControl extends BaseInput {
    private String label;
    private boolean selected;
    private Boolean correct;
}

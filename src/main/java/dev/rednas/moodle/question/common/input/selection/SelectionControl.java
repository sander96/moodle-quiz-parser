package dev.rednas.moodle.question.common.input.selection;

import dev.rednas.moodle.question.common.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectionControl extends BaseInput {
    private SelectionType selectionType;
    private boolean selected;
}

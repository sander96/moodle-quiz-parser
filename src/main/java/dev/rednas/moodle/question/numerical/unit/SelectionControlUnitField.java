package dev.rednas.moodle.question.numerical.unit;

import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectionControlUnitField implements UnitField {
    private List<SelectionControl> value;
}

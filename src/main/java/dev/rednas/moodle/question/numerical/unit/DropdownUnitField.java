package dev.rednas.moodle.question.numerical.unit;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DropdownUnitField implements UnitField {
    private Dropdown value;
}

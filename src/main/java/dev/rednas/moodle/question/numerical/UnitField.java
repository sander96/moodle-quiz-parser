package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import lombok.Data;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
public class UnitField {

    @Selector(value = "span.unitchoices", converter = RadioButtonConverter.class)
    private List<SelectionControl> radioButtons;

    @Selector(value = "select", converter = DropdownConverter.class)
    private Dropdown dropdown;

}

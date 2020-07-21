package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.parser.DropdownParser;
import dev.rednas.moodle.question.common.parser.SelectionControlParser;
import dev.rednas.moodle.question.numerical.unit.DropdownUnitField;
import dev.rednas.moodle.question.numerical.unit.SelectionControlUnitField;
import dev.rednas.moodle.question.numerical.unit.UnitField;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;

class UnitChoiceConverter implements ElementConverter<UnitField> {

    @Override
    public UnitField convert(Element node, Selector selector) {
        Element selectionControlUnitNode = node.selectFirst("span.unitchoices");
        if (selectionControlUnitNode != null) {
            return getSelectionControlUnitField(selectionControlUnitNode);
        }

        Element dropdownUnitNode = node.selectFirst("select");
        if (dropdownUnitNode != null) {
            return getDropdownUnitField(node);
        }

        return null;
    }

    private SelectionControlUnitField getSelectionControlUnitField(Element node) {
        List<SelectionControl> selectionControls = new ArrayList<>();
        for (Element child : node.children()) {
            selectionControls.add(SelectionControlParser.parse(child));
        }
        return new SelectionControlUnitField(selectionControls);
    }

    private DropdownUnitField getDropdownUnitField(Element node) {
        Dropdown dropdown = DropdownParser.parse(node);
        return new DropdownUnitField(dropdown);
    }
}

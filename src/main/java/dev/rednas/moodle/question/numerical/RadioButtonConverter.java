package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.parser.SelectionControlParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;

public class RadioButtonConverter implements ElementConverter<List<SelectionControl>> {
    @Override
    public List<SelectionControl> convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }

        List<SelectionControl> selectionControls = new ArrayList<>();
        for (Element child : node.children()) {
            selectionControls.add(SelectionControlParser.parse(child));
        }
        return selectionControls;
    }
}

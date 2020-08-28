package dev.rednas.moodle.question.common.parser;

import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectionControlParser {

    public static SelectionControl parse(Element node) {
        Element input = node.selectFirst("input");
        SelectionControl selectionControl = new SelectionControl();
        selectionControl.setId(input.id());
        selectionControl.setSelected(input.hasAttr("checked"));
        selectionControl.setSelectionType(parseType(input));
        InputGradeStateParser.parse(node).ifPresent(selectionControl::setGradeState);
        return selectionControl;
    }

    private static SelectionType parseType(Element inputNode) {
        String type = inputNode.attr("type").toUpperCase();
        return SelectionType.valueOf(type);
    }
}

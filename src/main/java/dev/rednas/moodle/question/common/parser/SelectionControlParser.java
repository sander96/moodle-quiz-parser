package dev.rednas.moodle.question.common.parser;

import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.selection.SelectionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectionControlParser {

    public static SelectionControl parse(Element node) {
        Element input = node.selectFirst("input");
        SelectionControl selectionControl = new SelectionControl();
        selectionControl.setId(input.id());
        selectionControl.setSelected(input.hasAttr("checked"));
        parseType(input).ifPresent(selectionControl::setSelectionType);
        InputGradeStateParser.parse(node).ifPresent(selectionControl::setGradeState);
        return selectionControl;
    }

    private static Optional<SelectionType> parseType(Element inputNode) {
        if (inputNode.hasAttr("type")) {
            String type = inputNode.attr("type").toUpperCase();
            return Optional.of(SelectionType.valueOf(type));
        }
        return Optional.empty();
    }
}

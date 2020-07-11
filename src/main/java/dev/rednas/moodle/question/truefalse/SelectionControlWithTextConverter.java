package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.parser.SelectionControlParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class SelectionControlWithTextConverter implements ElementConverter<InputWithText<SelectionControl>> {
    @Override
    public InputWithText<SelectionControl> convert(Element node, Selector selector) {
        InputWithText<SelectionControl> selectionWithText = new InputWithText<>();
        SelectionControl selectionControl = SelectionControlParser.parse(node);
        selectionWithText.setText(node.select("label").first().text());
        selectionWithText.setInput(selectionControl);
        return selectionWithText;
    }
}

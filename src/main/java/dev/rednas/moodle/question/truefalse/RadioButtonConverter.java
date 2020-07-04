package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.BaseSelectionControl;
import dev.rednas.moodle.question.common.input.selection.RadioButton;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class RadioButtonConverter implements ElementConverter<InputWithText<BaseSelectionControl>> {
    @Override
    public InputWithText<BaseSelectionControl> convert(Element node, Selector selector) {
        InputWithText<BaseSelectionControl> selectionWithText = new InputWithText<>();
        RadioButton radioButton = new RadioButton();

        Element input = node.select("input").first();
        radioButton.setId(input.id());
        radioButton.setSelected(input.hasAttr("checked"));
        selectionWithText.setText(node.select("label").first().text());

        if (node.hasClass("correct")) {
            radioButton.setCorrect(true);
        } else if (node.hasClass("incorrect")) {
            radioButton.setCorrect(false);
        }

        selectionWithText.setInput(radioButton);
        selectionWithText.setInput(radioButton);
        return selectionWithText;
    }
}

package dev.rednas.moodle.question.multichoice;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.parser.SelectionControlParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectionControlConverter implements ElementConverter<List<InputWithText<SelectionControl>>> {
    @Override
    public List<InputWithText<SelectionControl>> convert(Element node, Selector selector) {
        List<InputWithText<SelectionControl>> selections = new ArrayList<>();
        for (Element child : node.children()) {
            InputWithText<SelectionControl> inputWithText = new InputWithText<>();
            inputWithText.setText(child.selectFirst("label").text());
            inputWithText.setInput(SelectionControlParser.parse(child));
            getFeedback(child).ifPresent(inputWithText::setFeedback);
            selections.add(inputWithText);
        }
        return selections;
    }

    private static Optional<String> getFeedback(Element node) {
        Element feedbackElement = node.selectFirst("div.specificfeedback");
        if (feedbackElement == null) {
            return Optional.empty();
        }
        return Optional.of(feedbackElement.text());
    }
}

package dev.rednas.moodle.question.essay;

import dev.rednas.moodle.question.common.input.text.TextField;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.Optional;

class TextFieldConverter implements ElementConverter<TextField> {

    @Override
    public TextField convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }

        TextField textArea = new TextField();
        getTextareaValue(node).ifPresent(textArea::setValue);
        return textArea;
    }

    private static Optional<String> getTextareaValue(Element node) {
        Element element = node.selectFirst("textarea");
        if (element == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(element.text());
    }
}

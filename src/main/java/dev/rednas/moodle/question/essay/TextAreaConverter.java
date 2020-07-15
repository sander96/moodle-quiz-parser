package dev.rednas.moodle.question.essay;

import dev.rednas.moodle.question.common.input.textarea.TextArea;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.Optional;

public class TextAreaConverter implements ElementConverter<TextArea> {
    @Override
    public TextArea convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }

        TextArea textArea = new TextArea();
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

package dev.rednas.moodle.question.shortanswer;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.text.TextField;
import dev.rednas.moodle.question.common.parser.InputGradeStateParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class TextFieldConverter implements ElementConverter<InputWithText<TextField>> {
    @Override
    public InputWithText<TextField> convert(Element node, Selector selector) {
        InputWithText<TextField> textFieldWithText = new InputWithText<>();
        TextField textField = new TextField();
        Element inputElement = node.select("span > input").first();
        textField.setId(inputElement.id());
        textFieldWithText.setText(node.select("label").first().text());

        InputGradeStateParser.parse(inputElement).ifPresent(textField::setGradeState);

        textField.setValue(inputElement.attr("value"));
        textFieldWithText.setInput(textField);

        return textFieldWithText;
    }
}

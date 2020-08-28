package dev.rednas.moodle.question.shortanswer;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.text.TextField;
import dev.rednas.moodle.question.common.parser.InputGradeStateParser;
import dev.rednas.moodle.question.common.parser.TextFieldParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class TextFieldConverter implements ElementConverter<InputWithText<TextField>> {

    @Override
    public InputWithText<TextField> convert(Element node, Selector selector) {
        InputWithText<TextField> textFieldWithText = new InputWithText<>();
        Element inputElement = node.selectFirst("span > input");
        TextField textField = TextFieldParser.parse(inputElement);
        textFieldWithText.setText(node.selectFirst("label").text());
        InputGradeStateParser.parse(inputElement).ifPresent(textField::setGradeState);
        textFieldWithText.setInput(textField);
        return textFieldWithText;
    }
}

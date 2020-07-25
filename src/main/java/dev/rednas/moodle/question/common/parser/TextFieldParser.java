package dev.rednas.moodle.question.common.parser;

import dev.rednas.moodle.question.common.input.text.TextField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextFieldParser {

    public static TextField parse(Element node) {
        TextField textField = new TextField();
        textField.setId(node.id());
        InputGradeStateParser.parse(node).ifPresent(textField::setGradeState);
        textField.setValue(node.attr("value"));
        return textField;
    }
}

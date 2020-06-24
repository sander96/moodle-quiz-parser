package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.common.input.text.TextField;
import dev.rednas.moodle.question.shortanswer.ShortanswerQuestion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortAnswerParser {

    public static ShortanswerQuestion parse(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");

        ShortanswerQuestion question = new ShortanswerQuestion();

        question.setQuestionText(formulationElement.select("div.qtext").text());
        question.setTextField(parseTextField(formulationElement.select("div.ablock").first()));

        return question;
    }

    private static TextField parseTextField(Element textFieldElement) {
        TextField textField = new TextField();
        Element inputElement = textFieldElement.select("span > input").first();
        textField.setId(inputElement.id());
        textField.setLabel(textFieldElement.select("label").first().text());
        if (inputElement.hasClass("correct")) {
            textField.setCorrect(true);
        } else if (inputElement.hasClass("incorrect")) {
            textField.setCorrect(false);
        }
        textField.setValue(inputElement.attr("value"));

        return textField;
    }
}

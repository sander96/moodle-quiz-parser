package dev.rednas.moodle.question.type.shortanswer;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.question.type.common.input.text.TextField;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Getter
@Setter
public class ShortanswerQuestion extends Question {
    private String questionText;
    private TextField textField;

    private ShortanswerQuestion() {
        super(QuestionType.SHORTANSWER);
    }

    public static ShortanswerQuestion createInstance(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");

        ShortanswerQuestion question = new ShortanswerQuestion();

        question.questionText = formulationElement.select("div.qtext").text();
        question.textField = parseTextField(formulationElement.select("div.ablock").first());

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

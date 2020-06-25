package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.RadioButton;
import dev.rednas.moodle.question.truefalse.TrueFalseQuestion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrueFalseParser {

    public static TrueFalseQuestion parse(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");

        TrueFalseQuestion question = new TrueFalseQuestion();
        question.setQuestionText(formulationElement.select("div.qtext").text());
        question.setPrompt(formulationElement.select("div.prompt").text());

        Elements answerElement = formulationElement.select("div.answer");
        question.getSelectionControl().add(parseRadiobutton(answerElement.select(".r0").first()));
        question.getSelectionControl().add(parseRadiobutton(answerElement.select(".r1").first()));

        return question;
    }

    private static InputWithText<RadioButton> parseRadiobutton(Element radioButtonElement) {
        InputWithText<RadioButton> radioButtonWithText = new InputWithText<>();
        RadioButton radioButton = new RadioButton();
        Element input = radioButtonElement.select("input").first();
        radioButton.setId(input.id());
        radioButton.setSelected(input.hasAttr("checked"));
        radioButtonWithText.setText(radioButtonElement.select("label").first().text());
        if (radioButtonElement.hasClass("correct")) {
            radioButton.setCorrect(true);
        } else if (radioButtonElement.hasClass("incorrect")) {
            radioButton.setCorrect(false);
        }
        radioButtonWithText.setInput(radioButton);
        return radioButtonWithText;
    }
}

package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.common.input.selection.RadioButton;
import dev.rednas.moodle.question.truefalse.Answer;
import dev.rednas.moodle.question.truefalse.AnswerBlock;
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

        AnswerBlock answerBlock = new AnswerBlock();
        question.setAnswerBlock(answerBlock);
        answerBlock.setPrompt(formulationElement.select("div.prompt").text());

        Elements answerElement = formulationElement.select("div.answer");
        RadioButton radioButton1 = parserRadiobutton(answerElement.select(".r0").first());
        RadioButton radioButton2 = parserRadiobutton(answerElement.select(".r1").first());

        Answer answer = new Answer();
        answer.setRadioButtonTrue(radioButton1);
        answer.setRadioButtonFalse(radioButton2);
        answerBlock.setAnswer(answer);

        return question;
    }

    private static RadioButton parserRadiobutton(Element radioButtonElement) {
        RadioButton radioButton = new RadioButton();
        Element input = radioButtonElement.select("input").first();
        radioButton.setId(input.id());
        radioButton.setSelected(input.hasAttr("checked"));
        radioButton.setLabel(radioButtonElement.select("label").first().text());
        if (radioButtonElement.hasClass("correct")) {
            radioButton.setCorrect(true);
        } else if (radioButtonElement.hasClass("incorrect")) {
            radioButton.setCorrect(false);
        }
        return radioButton;
    }
}

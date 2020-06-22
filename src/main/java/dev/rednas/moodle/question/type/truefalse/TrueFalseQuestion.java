package dev.rednas.moodle.question.type.truefalse;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.question.type.common.input.selection.RadioButton;
import lombok.Getter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Getter
public class TrueFalseQuestion extends Question {

    private String questionText;
    private final AnswerBlock answerBlock = new AnswerBlock();

    private TrueFalseQuestion() {
        super(QuestionType.TRUEFALSE);
    }

    public static TrueFalseQuestion createInstance(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");

        TrueFalseQuestion question = new TrueFalseQuestion();
        question.questionText = formulationElement.select("div.qtext").text();

        question.answerBlock.setPrompt(formulationElement.select("div.prompt").text());

        Elements answerElement = formulationElement.select("div.answer");
        RadioButton radioButton1 = parserRadiobutton(answerElement.select(".r0").first());
        RadioButton radioButton2 = parserRadiobutton(answerElement.select(".r1").first());

        Answer answer = new Answer();
        answer.setRadioButtonTrue(radioButton1);
        answer.setRadioButtonFalse(radioButton2);
        question.answerBlock.setAnswer(answer);

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

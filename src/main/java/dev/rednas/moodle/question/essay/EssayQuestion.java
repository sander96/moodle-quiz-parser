package dev.rednas.moodle.question.essay;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.common.input.textarea.TextArea;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class EssayQuestion extends GradedQuestion {

    @Selector(value = "div.qtext")
    private String questionText;

    @Selector(value = "div.answer", converter = TextAreaConverter.class)
    private TextArea textArea;

    @Selector(value = "div.attachments > p > a", attr = "href")
    private List<String> files;
}

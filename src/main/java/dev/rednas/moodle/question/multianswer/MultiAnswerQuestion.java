package dev.rednas.moodle.question.multianswer;

import dev.rednas.moodle.question.GradedQuestion;
import dev.rednas.moodle.question.multianswer.subquestion.Subquestion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Selector(value = "div.content > div.formulation")
public class MultiAnswerQuestion extends GradedQuestion {

    @Selector(value = "pre", converter = QuestionTextConverter.class)
    private String questionText;

    @Selector(value = "pre", converter = SubquestionConverter.class)
    private List<Subquestion> subquestions;
}

package dev.rednas.moodle.question;

import dev.rednas.moodle.question.info.QuestionInfo;
import dev.rednas.moodle.question.info.Outcome;
import lombok.Data;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
public abstract class GradedQuestion implements Question {

    @Selector(value = "div.info")
    private QuestionInfo info;

    @Selector(value = "div.outcome")
    private Outcome outcome;
}

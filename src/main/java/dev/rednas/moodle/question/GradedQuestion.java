package dev.rednas.moodle.question;

import lombok.Data;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
public abstract class GradedQuestion implements Question {

    @Selector(value = "div.info")
    private QuestionInfo info;
}


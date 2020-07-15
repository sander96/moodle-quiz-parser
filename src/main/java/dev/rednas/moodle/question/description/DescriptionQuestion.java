package dev.rednas.moodle.question.description;

import dev.rednas.moodle.question.Question;
import lombok.Data;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@Selector(value = "div.content > div.formulation")
public class DescriptionQuestion implements Question {

    @Selector(value = "div.qtext")
    private String questionText;

}

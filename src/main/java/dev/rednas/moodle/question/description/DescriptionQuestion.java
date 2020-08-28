package dev.rednas.moodle.question.description;

import dev.rednas.moodle.question.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@EqualsAndHashCode
@Selector(value = "div.content > div.formulation")
public class DescriptionQuestion implements Question {

    @Selector(value = "div.qtext")
    private String questionText;
}

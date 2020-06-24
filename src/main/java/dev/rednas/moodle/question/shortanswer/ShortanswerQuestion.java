package dev.rednas.moodle.question.shortanswer;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.text.TextField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShortanswerQuestion extends Question {
    private String questionText;
    private TextField textField;
}

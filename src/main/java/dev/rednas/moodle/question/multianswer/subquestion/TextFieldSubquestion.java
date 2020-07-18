package dev.rednas.moodle.question.multianswer.subquestion;

import dev.rednas.moodle.question.common.input.text.TextField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextFieldSubquestion extends Subquestion {
    private TextField value;
    private String feedback;
}

package dev.rednas.moodle.question.multianswer.subquestion;

import dev.rednas.moodle.question.common.input.text.TextField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TextFieldSubquestion implements Subquestion {
    private TextField value;
    private String feedback;
}

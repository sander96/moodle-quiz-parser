package dev.rednas.moodle.question.common.input.textarea;

import dev.rednas.moodle.question.common.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextArea extends BaseInput {
    private String value;
}

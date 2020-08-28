package dev.rednas.moodle.question.common.input.text;

import dev.rednas.moodle.question.common.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextField extends BaseInput {
    private String value;
}

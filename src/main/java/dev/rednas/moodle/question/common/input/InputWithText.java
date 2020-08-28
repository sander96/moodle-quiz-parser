package dev.rednas.moodle.question.common.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class InputWithText<T extends BaseInput> {
    private String text;
    private T input;
    private String feedback;
}

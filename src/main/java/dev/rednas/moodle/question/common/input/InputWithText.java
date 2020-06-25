package dev.rednas.moodle.question.common.input;

import lombok.Data;

@Data
public class InputWithText<T extends BaseInput> {
    private String text;
    private T input;
}

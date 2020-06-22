package dev.rednas.moodle.question.type.common.input;

import lombok.Data;

@Data
public abstract class BaseInput {
    private String id;
    private final InputType type;
    private Boolean correct;
}

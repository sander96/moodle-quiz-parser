package dev.rednas.moodle.question.common.input;

import dev.rednas.moodle.question.GradeState;
import lombok.Data;

@Data
public abstract class BaseInput {
    private String id;
    private GradeState gradeState;
}

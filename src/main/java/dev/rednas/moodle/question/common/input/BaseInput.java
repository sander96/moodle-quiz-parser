package dev.rednas.moodle.question.common.input;

import dev.rednas.moodle.question.info.GradeState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class BaseInput {
    private String id;
    private GradeState gradeState;
}

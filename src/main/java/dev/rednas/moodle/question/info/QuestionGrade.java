package dev.rednas.moodle.question.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class QuestionGrade {
    private Float mark;
    private Float max;
}

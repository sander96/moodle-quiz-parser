package dev.rednas.moodle.question.info;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
@EqualsAndHashCode
public class QuestionInfo {

    @Selector(value = "h3.no > span.qno")
    private Long number;

    @Selector(value = "div.state", converter = QuizStateConverter.class)
    private String state;

    @Selector(value = "div.grade", converter = QuestionGradeConverter.class)
    private QuestionGrade grade;
}

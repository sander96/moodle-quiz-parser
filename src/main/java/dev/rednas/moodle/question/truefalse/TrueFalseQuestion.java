package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrueFalseQuestion extends Question {
    private String questionText;
    private AnswerBlock answerBlock;
}

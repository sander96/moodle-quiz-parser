package dev.rednas.moodle.question.type.truefalse;

import lombok.Data;

@Data
public class AnswerBlock {
    private String prompt;
    private Answer answer;
}

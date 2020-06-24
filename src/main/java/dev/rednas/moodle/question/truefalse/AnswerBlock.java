package dev.rednas.moodle.question.truefalse;

import lombok.Data;

@Data
public class AnswerBlock {
    private String prompt;
    private Answer answer;
}

package dev.rednas.moodle.question;

import lombok.Data;

@Data
public class QuestionInfo {
    private Long number;
    private String state;
    private QuestionGrade grade;
}

package dev.rednas.moodle.question;

import lombok.Data;

@Data
public class Question {
    private QuestionType type;
    private QuestionInfo info;
    private QuestionGrade grade;
}

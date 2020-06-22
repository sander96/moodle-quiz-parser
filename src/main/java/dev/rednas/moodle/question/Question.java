package dev.rednas.moodle.question;

import lombok.Data;

@Data
public abstract class Question {
    private final QuestionType type;
    private QuestionInfo info;
}

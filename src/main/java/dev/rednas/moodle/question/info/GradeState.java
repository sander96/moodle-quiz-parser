package dev.rednas.moodle.question.info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GradeState {
    INCORRECT("incorrect"),
    CORRECT("correct"),
    PARTIALLY_CORRECT("partiallycorrect");

    @Getter
    private final String cssClass;
}

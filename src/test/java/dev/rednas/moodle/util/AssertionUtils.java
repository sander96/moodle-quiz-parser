package dev.rednas.moodle.util;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssertionUtils {

    public static void assertQuestionInfo(Question question, String mark, String max,
                                          Long number, String state, QuestionType questionType) {
        assertEquals(mark, question.getInfo().getGrade().getMark());
        assertEquals(max, question.getInfo().getGrade().getMax());
        assertEquals(number, question.getInfo().getNumber());
        assertEquals(state, question.getInfo().getState());
        assertEquals(questionType, question.getType());
    }
}

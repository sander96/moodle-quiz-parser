package dev.rednas.moodle.question;

import dev.rednas.moodle.question.calculated.CalculatedQuestion;
import dev.rednas.moodle.question.calculatedsimple.CalculatedSimpleQuestion;
import dev.rednas.moodle.question.match.MatchQuestion;
import dev.rednas.moodle.question.multichoice.Multichoice;
import dev.rednas.moodle.question.numerical.NumericalQuestion;
import dev.rednas.moodle.question.shortanswer.ShortanswerQuestion;
import dev.rednas.moodle.question.truefalse.TrueFalseQuestion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestionType {
    TRUEFALSE(TrueFalseQuestion.class),
    SHORTANSWER(ShortanswerQuestion.class),
    MATCH(MatchQuestion.class),
    NUMERICAL(NumericalQuestion.class),
    MULTICHOICE(Multichoice.class),
    CALCULATED(CalculatedQuestion.class),
    CALCULATEDSIMPLE(CalculatedSimpleQuestion.class);

    @Getter
    private final Class<? extends Question> questionClass;
}

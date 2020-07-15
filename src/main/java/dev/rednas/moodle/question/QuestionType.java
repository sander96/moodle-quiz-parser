package dev.rednas.moodle.question;

import dev.rednas.moodle.question.calculated.CalculatedQuestion;
import dev.rednas.moodle.question.calculatedmulti.CalculatedMultiQuestion;
import dev.rednas.moodle.question.calculatedsimple.CalculatedSimpleQuestion;
import dev.rednas.moodle.question.description.DescriptionQuestion;
import dev.rednas.moodle.question.essay.EssayQuestion;
import dev.rednas.moodle.question.match.MatchQuestion;
import dev.rednas.moodle.question.multichoice.Multichoice;
import dev.rednas.moodle.question.numerical.NumericalQuestion;
import dev.rednas.moodle.question.randomsamatch.RandomShortAnswerQuestion;
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
    CALCULATEDSIMPLE(CalculatedSimpleQuestion.class),
    CALCULATEDMULTI(CalculatedMultiQuestion.class),
    DESCRIPTION(DescriptionQuestion.class),
    ESSAY(EssayQuestion.class),
    RANDOMSAMATCH(RandomShortAnswerQuestion.class);

    @Getter
    private final Class<? extends Question> questionClass;
}

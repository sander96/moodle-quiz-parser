package dev.rednas.moodle.question;

import dev.rednas.moodle.question.type.truefalse.TrueFalseQuestion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionFactory {

    public static Question create(QuestionType type, Element contentElement) {
        if (QuestionType.TRUEFALSE.equals(type)) {
            return TrueFalseQuestion.createInstance(contentElement);
        }
        throw new RuntimeException(type + " question type is not implemented yet");
    }
}

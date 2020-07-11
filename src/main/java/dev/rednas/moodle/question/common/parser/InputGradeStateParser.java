package dev.rednas.moodle.question.common.parser;

import dev.rednas.moodle.question.GradeState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputGradeStateParser {

    public static Optional<GradeState> parse(Element node) {
        if (node == null) {
            return Optional.empty();
        }

        if (node.hasClass("correct")) {
            return Optional.of(GradeState.CORRECT);
        } else if (node.hasClass("incorrect")) {
            return Optional.of(GradeState.INCORRECT);
        } else if (node.hasClass("partiallycorrect")) {
            return Optional.of(GradeState.PARTIALLY_CORRECT);
        }
        return Optional.empty();
    }
}

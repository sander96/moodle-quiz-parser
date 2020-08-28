package dev.rednas.moodle.quiz;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.Jspoon;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizParser {

    public static Quiz parse(String htmlDocument) {
        Document document = Jsoup.parse(htmlDocument);
        document.outputSettings().indentAmount(0).prettyPrint(false);
        Quiz quiz = new Quiz();
        quiz.setQuestions(parseQuestions(document));
        return quiz;
    }

    private static List<Question> parseQuestions(Document document) {
        Elements questionElements = document.select("div.que");
        List<Question> questions = new ArrayList<>();

        for (Element questionElement : questionElements) {
            try {
                questions.add(parseQuestion(questionElement));
            } catch (Exception e) {
                log.warn("Could not parse a question", e);
                questions.add(null);
            }
        }
        return questions;
    }

    private static Question parseQuestion(Element questionElement) {
        QuestionType questionType = parseQuestionType(questionElement);
        return parseQuestion(questionType, questionElement);
    }

    private static QuestionType parseQuestionType(Element questionElement) {
        List<String> classNames = new ArrayList<>(questionElement.classNames());
        String questionType = classNames.get(1).toUpperCase();
        try {
            return QuestionType.valueOf(questionType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Question type '" + questionType + "' is not implemented", e);
        }
    }

    private static Question parseQuestion(QuestionType type, Element questionElement) {
        return Jspoon.create()
                .adapter(type.getQuestionClass())
                .fromHtml(questionElement.outerHtml());
    }
}

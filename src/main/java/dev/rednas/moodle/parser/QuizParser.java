package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.quiz.Quiz;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.Jspoon;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizParser {

    public static Quiz parse(String htmlDocument) {
        Document document = Jsoup.parse(htmlDocument);
        Quiz quiz = new Quiz();
        quiz.setQuestions(parseQuestions(document));
        return quiz;
    }

    private static List<Question> parseQuestions(Document document) {
        Elements questionElements = document.select("div.que");
        List<Question> questions = new ArrayList<>();

        for (Element questionElement : questionElements) {
            questions.add(parseQuestion(questionElement));
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
            throw new RuntimeException("Question type '" + questionType + "' is not implemented");
        }
    }

    private static Question parseQuestion(QuestionType type, Element questionElement) {
        return Jspoon.create()
                .adapter(type.getQuestionClass())
                .fromHtml(questionElement.outerHtml());
    }
}

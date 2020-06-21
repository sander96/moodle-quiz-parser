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

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizParser {

    public static Quiz parse(String htmlDocument) {
        Document document = Jsoup.parse(htmlDocument);
        List<Question> questions = parseQuestions(document);

        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
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


        Element infoElement = questionElement.selectFirst("div.que > .info");
        Element contentElement = questionElement.selectFirst("div.que > .content");

        Element outcomeElement = questionElement.selectFirst("div.que > outcome");
        Element commentElement = questionElement.selectFirst("div.que > comment");
        Element historyElement = questionElement.selectFirst("div.que > history");

        Question question = new Question();
        question.setQuestionType(questionType);

        return question;
    }

    private static QuestionType parseQuestionType(Element questionElement) {
        Set<String> classNames = questionElement.classNames();
        Iterator<String> iterator = classNames.iterator();
        iterator.next();
        String questionType = iterator.next();
        return QuestionType.valueOf(questionType.toUpperCase());
    }

}

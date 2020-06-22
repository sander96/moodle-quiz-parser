package dev.rednas.moodle.parser;

import dev.rednas.moodle.language.LanguageComponent;
import dev.rednas.moodle.language.LanguageUtils;
import dev.rednas.moodle.question.*;
import dev.rednas.moodle.quiz.Quiz;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Element contentElement = questionElement.selectFirst("div.que > div.content");
        Question question = QuestionFactory.create(questionType, contentElement);

        QuestionInfo questionInfo = parseQuestionInfo(questionElement);
        question.setInfo(questionInfo);

        return question;
    }

    private static QuestionType parseQuestionType(Element questionElement) {
        Set<String> classNames = questionElement.classNames();
        Iterator<String> iterator = classNames.iterator();
        iterator.next();
        String questionType = iterator.next();
        return QuestionType.valueOf(questionType.toUpperCase());
    }

    private static QuestionInfo parseQuestionInfo(Element questionElement) {
        Element infoElement = questionElement.selectFirst("div.que > div.info");
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setNumber(parseQuestionNumber(infoElement));
        questionInfo.setState(parseQuestionState(infoElement));
        questionInfo.setGrade(parseGrade(infoElement));
        return questionInfo;
    }

    private static Long parseQuestionNumber(Element infoElement) {
        Elements questionNumberElement = infoElement.select("div.info > h3.no > span.qno");
        if (questionNumberElement.isEmpty()) {
            return null;
        }
        return Long.valueOf(questionNumberElement.text());
    }

    private static String parseQuestionState(Element infoElement) {
        String state = infoElement.select("div.info > div.state").text();
        return LanguageUtils.getIdentifier(state, LanguageComponent.QUESTION);
    }

    private static QuestionGrade parseGrade(Element infoElement) {
        Elements gradeElements = infoElement.select("div.info > div.grade");
        if (gradeElements.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("(\\d+[,.]*\\d+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(gradeElements.text());

        QuestionGrade grade = new QuestionGrade();

        if (matcher.find()) {
            grade.setMark(matcher.group(0));
        }

        if (matcher.find()) {
            grade.setMax(matcher.group(0));
        } else {
            grade.setMax(grade.getMark());
            grade.setMark(null);
        }

        return grade;
    }

}

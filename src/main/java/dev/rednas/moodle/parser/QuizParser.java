package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.question.calculated.CalculatedQuestion;
import dev.rednas.moodle.question.match.MatchQuestion;
import dev.rednas.moodle.question.multichoice.Multichoice;
import dev.rednas.moodle.question.numerical.NumericalQuestion;
import dev.rednas.moodle.question.shortanswer.ShortanswerQuestion;
import dev.rednas.moodle.question.truefalse.TrueFalseQuestion;
import dev.rednas.moodle.quiz.Quiz;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.Jspoon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
        Set<String> classNames = questionElement.classNames();
        Iterator<String> iterator = classNames.iterator();
        iterator.next();
        String questionType = iterator.next();
        return QuestionType.valueOf(questionType.toUpperCase());
    }

    private static Question parseQuestion(QuestionType type, Element questionElement) {
        if (QuestionType.TRUEFALSE.equals(type)) {
            return Jspoon.create()
                    .adapter(TrueFalseQuestion.class)
                    .fromHtml(questionElement.outerHtml());
        } else if (QuestionType.SHORTANSWER.equals(type)) {
            return Jspoon.create()
                    .adapter(ShortanswerQuestion.class)
                    .fromHtml(questionElement.outerHtml());
        } else if (QuestionType.MATCH.equals(type)) {
            return Jspoon.create()
                    .adapter(MatchQuestion.class)
                    .fromHtml(questionElement.outerHtml());
        } else if (QuestionType.NUMERICAL.equals(type)) {
            return Jspoon.create()
                    .adapter(NumericalQuestion.class)
                    .fromHtml(questionElement.outerHtml());
        } else if (QuestionType.MULTICHOICE.equals(type)) {
            return Jspoon.create()
                    .adapter(Multichoice.class)
                    .fromHtml(questionElement.outerHtml());
        } else if (QuestionType.CALCULATED.equals(type)) {
            return Jspoon.create()
                    .adapter(CalculatedQuestion.class)
                    .fromHtml(questionElement.outerHtml());
        }
        throw new RuntimeException(type + " question type is not implemented yet");
    }
}

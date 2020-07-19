package dev.rednas.moodle.question.info;

import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionGradeConverter implements ElementConverter<QuestionGrade> {
    @Override
    public QuestionGrade convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("(-?\\d+[,.]*\\d+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(node.text());

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

package dev.rednas.moodle.question.info;

import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QuestionGradeConverter implements ElementConverter<QuestionGrade> {
    public static final String NUMBER_REGEX = "(-?\\d+[.,]?\\d*)";

    @Override
    public QuestionGrade convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }

        String gradeText = node.text();

        if (isMarkedOutOfMax(gradeText)) {
            return createMarkedOutOfMax(gradeText);
        } else if (isMarkOutOfMax(gradeText)) {
            return createMarkOutOfMax(gradeText);
        }

        throw new IllegalArgumentException("Grade text does not contain 1 or 2 numbers.");
    }

    private static boolean isMarkedOutOfMax(String localizedString) {
        return findAllNumbers(localizedString).size() == 1;
    }

    private static QuestionGrade createMarkedOutOfMax(String localizedString) {
        QuestionGrade grade = new QuestionGrade();
        List<Float> numbers = findAllNumbers(localizedString);
        grade.setMax(numbers.get(0));
        return grade;
    }

    private static boolean isMarkOutOfMax(String localizedString) {
        return findAllNumbers(localizedString).size() == 2;
    }

    private QuestionGrade createMarkOutOfMax(String localizedString) {
        QuestionGrade grade = new QuestionGrade();
        List<Float> numbers = findAllNumbers(localizedString);

        float grade1 = numbers.get(0);
        float grade2 = numbers.get(1);

        if (grade1 == grade2 || grade1 < grade2) {
            grade.setMark(grade1);
            grade.setMax(grade2);
        } else {
            grade.setMark(grade2);
            grade.setMax(grade1);
        }

        return grade;
    }

    private static List<Float> findAllNumbers(String localizedString) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(localizedString);
        List<Float> numbers = new ArrayList<>();

        while (matcher.find()) {
            String number = matcher.group();
            numbers.add(parseFloat(number));
        }

        return numbers;
    }

    private static float parseFloat(String number) {
        return Float.parseFloat(number.replace(",", "."));
    }
}

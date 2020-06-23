package dev.rednas.moodle.question.type.match;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.QuestionType;
import dev.rednas.moodle.question.type.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.type.common.input.dropdown.Option;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MatchQuestion extends Question {

    private String questionText;
    private List<Dropdown> dropdowns = new ArrayList<>();

    private MatchQuestion() {
        super(QuestionType.MATCH);
    }

    public static MatchQuestion createInstance(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");
        MatchQuestion question = new MatchQuestion();
        question.questionText = formulationElement.select("div.qtext").first().text();

        question.dropdowns = parseTable(contentElement);

        return question;
    }

    private static List<Dropdown> parseTable(Element formulationElement) {
        Elements tableRows = formulationElement.select("table > tbody > tr");
        List<Dropdown> dropdowns = new ArrayList<>();
        for (Element tableRow : tableRows) {
            Dropdown dropdown = new Dropdown();
            dropdown.setId(tableRow.selectFirst("select").id());
            dropdown.setText(tableRow.select("td.text").text());
            dropdown.setOptions(parseOptions(tableRow));

            Element element = tableRow.selectFirst("td.control");
            if (element.hasClass("correct")) {
                dropdown.setCorrect(true);
            } else if (element.hasClass("incorrect")) {
                dropdown.setCorrect(false);
            }
            dropdowns.add(dropdown);
        }
        return dropdowns;
    }

    private static List<Option> parseOptions(Element tableRow) {
        List<Option> options = new ArrayList<>();
        Elements optionElements = tableRow.select("option");
        for (Element optionElement : optionElements) {
            Option option = new Option();
            option.setValue(optionElement.attr("value"));
            option.setSelected(optionElement.hasAttr("selected"));
            option.setText(optionElement.text());
            options.add(option);
        }

        return options;
    }
}

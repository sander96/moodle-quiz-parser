package dev.rednas.moodle.parser;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.dropdown.Option;
import dev.rednas.moodle.question.match.MatchQuestion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchParser {

    public static MatchQuestion parse(Element contentElement) {
        Elements formulationElement = contentElement.select("div.content > div.formulation");
        MatchQuestion question = new MatchQuestion();
        question.setQuestionText(formulationElement.select("div.qtext").first().text());

        question.setDropdowns(parseTable(contentElement));

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

package dev.rednas.moodle.question.common.parser;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.dropdown.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DropdownParser {

    public static Dropdown parse(Element node) {
        return parse(node, "td.control");
    }

    public static Dropdown parse(Element node, String correctnessCss) {
        Dropdown dropdown = new Dropdown();
        dropdown.setId(node.selectFirst("select").id());
        dropdown.setOptions(parseOptions(node));

        Element element = node.selectFirst(correctnessCss);
        InputGradeStateParser.parse(element).ifPresent(dropdown::setGradeState);
        return dropdown;
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

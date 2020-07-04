package dev.rednas.moodle.question.match;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.input.dropdown.Option;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;

public class DropdownConverter implements ElementConverter<List<InputWithText<Dropdown>>> {
    @Override
    public List<InputWithText<Dropdown>> convert(Element node, Selector selector) {
        Elements tableRows = node.select("table > tbody > tr");
        List<InputWithText<Dropdown>> dropdowns = new ArrayList<>();
        for (Element tableRow : tableRows) {
            InputWithText<Dropdown> dropdownWithText = new InputWithText<>();
            dropdownWithText.setText(tableRow.select("td.text").text());

            Dropdown dropdown = new Dropdown();
            dropdownWithText.setInput(dropdown);
            dropdown.setId(tableRow.selectFirst("select").id());
            dropdown.setOptions(parseOptions(tableRow));

            Element element = tableRow.selectFirst("td.control");
            if (element.hasClass("correct")) {
                dropdown.setCorrect(true);
            } else if (element.hasClass("incorrect")) {
                dropdown.setCorrect(false);
            }
            dropdowns.add(dropdownWithText);
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

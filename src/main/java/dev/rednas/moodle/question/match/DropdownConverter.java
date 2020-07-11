package dev.rednas.moodle.question.match;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.parser.DropdownParser;
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
            dropdownWithText.setInput(DropdownParser.parse(tableRow));
            dropdowns.add(dropdownWithText);
        }
        return dropdowns;
    }
}

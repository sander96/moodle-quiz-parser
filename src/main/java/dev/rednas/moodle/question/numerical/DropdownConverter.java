package dev.rednas.moodle.question.numerical;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.parser.DropdownParser;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class DropdownConverter implements ElementConverter<Dropdown> {
    @Override
    public Dropdown convert(Element node, Selector selector) {
        if (node == null) {
            return null;
        }
        return DropdownParser.parse(node);
    }
}

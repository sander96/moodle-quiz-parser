package dev.rednas.moodle.question.gapselect;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import dev.rednas.moodle.question.common.parser.DropdownParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;

class DropdownConverter implements ElementConverter<List<Dropdown>> {

    @Override
    public List<Dropdown> convert(Element node, Selector selector) {
        Elements spanNodes = node.getElementsByTag("span");
        List<Dropdown> dropdowns = new ArrayList<>();

        for (Element spanNode : spanNodes) {
            dropdowns.add(DropdownParser.parse(spanNode, "select"));
        }
        return dropdowns;
    }
}

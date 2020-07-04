package dev.rednas.moodle.language;

import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class LanguageComponentConverter implements ElementConverter<String> {
    @Override
    public String convert(Element node, Selector selector) {
        return LanguageUtils.getIdentifier(node.text(), LanguageComponent.QUESTION);
    }
}

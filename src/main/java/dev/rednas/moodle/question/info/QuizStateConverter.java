package dev.rednas.moodle.question.info;

import dev.rednas.moodle.language.LanguageUtils;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

class QuizStateConverter implements ElementConverter<String> {

    @Override
    public String convert(Element node, Selector selector) {
        return LanguageUtils.getIdentifier(node.text()).orElse(null);
    }
}

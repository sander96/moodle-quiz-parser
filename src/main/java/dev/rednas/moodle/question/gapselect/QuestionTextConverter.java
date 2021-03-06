package dev.rednas.moodle.question.gapselect;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

class QuestionTextConverter implements ElementConverter<String> {

    @Override
    public String convert(Element node, Selector selector) {
        Element clonedNode = node.clone();
        Elements spanNodes = clonedNode.getElementsByTag("span");

        for (int i = 0; i < spanNodes.size(); i++) {
            spanNodes.get(i).replaceWith(createNumberNode(i));
        }
        return clonedNode.text();
    }

    private static TextNode createNumberNode(int index) {
        return new TextNode("[[" + (index) + "]]");
    }
}

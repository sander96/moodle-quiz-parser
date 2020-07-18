package dev.rednas.moodle.question.multianswer;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

public class QuestionTextConverter implements ElementConverter<String> {
    @Override
    public String convert(Element node, Selector selector) {
        Element updatedNode = SubquestionParser.wrapSubquestions(node);
        Elements subquestions = updatedNode.select(".subquestion");

        for (int i = 0; i < subquestions.size(); i++) {
            subquestions.get(i).replaceWith(createNumberNode(i));
        }

        return updatedNode.wholeText();
    }

    private static TextNode createNumberNode(int index) {
        return new TextNode("{" + (index) + "}");
    }
}

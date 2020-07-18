package dev.rednas.moodle.question.multianswer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubquestionParser {

    public static Element wrapSubquestions(Element node) {
        Element clonedNode = node.clone();
        wrapMultichoiceSubquestions(clonedNode);
        return clonedNode;
    }

    private static void wrapMultichoiceSubquestions(Element node) {
        Elements subquestions = node.select(".answer");
        for (Element subquestion : subquestions) {
            Optional<Element> outcomeNode = getMultichoiceOutcomeNode(subquestion);
            subquestion.wrap(getWrapHtml());
            outcomeNode.ifPresent(element -> {
                element.remove();
                subquestion.parent().appendChild(element);
            });
        }
    }

    private static Optional<Element> getMultichoiceOutcomeNode(Element multichoiceAnswerNode) {
        Element sibling = multichoiceAnswerNode.nextElementSibling();
        if (sibling != null && sibling.hasClass("outcome")) {
            return Optional.of(sibling);
        }
        return Optional.empty();
    }

    private static String getWrapHtml() {
        return new Element("div").addClass("subquestion").toString();
    }
}

package dev.rednas.moodle.question.multianswer;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import dev.rednas.moodle.question.common.input.text.TextField;
import dev.rednas.moodle.question.common.parser.DropdownParser;
import dev.rednas.moodle.question.common.parser.SelectionControlParser;
import dev.rednas.moodle.question.multianswer.subquestion.DropdownSubquestion;
import dev.rednas.moodle.question.multianswer.subquestion.MultichoiceSubquestion;
import dev.rednas.moodle.question.multianswer.subquestion.Subquestion;
import dev.rednas.moodle.question.multianswer.subquestion.TextFieldSubquestion;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubquestionConverter implements ElementConverter<List<Subquestion>> {

    @Override
    public List<Subquestion> convert(Element node, Selector selector) {
        Elements subquestionElements = SubquestionParser.wrapSubquestions(node).select(".subquestion");
        List<Subquestion> subquestions = new ArrayList<>();

        for (Element subquestion : subquestionElements) {
            subquestions.add(getSubquestion(subquestion));
        }

        return subquestions;
    }

    private static Subquestion getSubquestion(Element subquestionNode) {
        if (subquestionNode.selectFirst("select") != null) {
            return getDropdown(subquestionNode);
        } else if (subquestionNode.selectFirst("input[type=text]") != null) {
            return getTextField(subquestionNode);
        }
        return getMultichoice(subquestionNode);
    }

    private static DropdownSubquestion getDropdown(Element subquestionNode) {
        DropdownSubquestion subquestion = new DropdownSubquestion();
        subquestion.setValue(DropdownParser.parse(subquestionNode, "select"));
        getFeedback(subquestionNode, "span.feedbackspan").ifPresent(subquestion::setFeedback);
        return subquestion;
    }

    private static TextFieldSubquestion getTextField(Element subquestionNode) {
        TextFieldSubquestion subquestion = new TextFieldSubquestion();
        TextField textField = new TextField();
        Element input = subquestionNode.selectFirst("input");
        textField.setValue(input.attr("value"));
        subquestion.setValue(textField);
        getFeedback(subquestionNode, "span.feedbackspan").ifPresent(subquestion::setFeedback);
        return subquestion;
    }

    private static MultichoiceSubquestion getMultichoice(Element subquestionNode) {
        List<InputWithText<SelectionControl>> list = new ArrayList<>();
        Element element = subquestionNode.selectFirst(".answer");

        for (Element child : element.children()) {
            InputWithText<SelectionControl> selection = new InputWithText<>();
            selection.setInput(SelectionControlParser.parse(child));
            selection.setText(child.selectFirst("label").text());
            getFeedback(child, "div.specificfeedback").ifPresent(selection::setFeedback);
            list.add(selection);
        }

        MultichoiceSubquestion subquestion = new MultichoiceSubquestion();
        subquestion.setValue(list);
        getFeedback(subquestionNode, ".outcome").ifPresent(subquestion::setFeedback);
        return subquestion;
    }

    private static Optional<String> getFeedback(Element subquestionNode, String cssSelector) {
        Element feedback = subquestionNode.selectFirst(cssSelector);
        if (feedback == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(feedback.text());
    }
}

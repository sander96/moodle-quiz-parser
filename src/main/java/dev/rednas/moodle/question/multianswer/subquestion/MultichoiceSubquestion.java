package dev.rednas.moodle.question.multianswer.subquestion;

import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.SelectionControl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class MultichoiceSubquestion implements Subquestion {
    private List<InputWithText<SelectionControl>> value;
    private String feedback;
}

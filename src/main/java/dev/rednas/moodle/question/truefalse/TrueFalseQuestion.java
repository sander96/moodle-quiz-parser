package dev.rednas.moodle.question.truefalse;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.selection.RadioButton;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrueFalseQuestion extends Question {
    private String questionText;
    private String prompt;
    private List<InputWithText<RadioButton>> selectionControl = new ArrayList<>();
}

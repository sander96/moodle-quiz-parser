package dev.rednas.moodle.question.match;

import dev.rednas.moodle.question.Question;
import dev.rednas.moodle.question.common.input.InputWithText;
import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchQuestion extends Question {
    private String questionText;
    private List<InputWithText<Dropdown>> dropdowns = new ArrayList<>();
}

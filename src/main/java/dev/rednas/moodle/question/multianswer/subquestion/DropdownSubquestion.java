package dev.rednas.moodle.question.multianswer.subquestion;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DropdownSubquestion extends Subquestion {
    private Dropdown value;
    private String feedback;
}

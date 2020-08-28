package dev.rednas.moodle.question.multianswer.subquestion;

import dev.rednas.moodle.question.common.input.dropdown.Dropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DropdownSubquestion implements Subquestion {
    private Dropdown value;
    private String feedback;
}

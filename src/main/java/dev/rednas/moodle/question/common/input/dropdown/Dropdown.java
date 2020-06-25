package dev.rednas.moodle.question.common.input.dropdown;

import dev.rednas.moodle.question.common.input.BaseInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Dropdown extends BaseInput {
    private List<Option> options;
    private Boolean correct;
}

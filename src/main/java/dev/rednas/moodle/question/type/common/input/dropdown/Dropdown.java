package dev.rednas.moodle.question.type.common.input.dropdown;

import dev.rednas.moodle.question.type.common.input.BaseInput;
import dev.rednas.moodle.question.type.common.input.InputType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dropdown extends BaseInput {

    private String text;
    private List<Option> options;
    private Boolean correct;

    public Dropdown() {
        super(InputType.DROPDOWN);
    }
}

package dev.rednas.moodle.question.type.common.input.dropdown;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option {
    private String value;
    private String text;
    private boolean selected;
}

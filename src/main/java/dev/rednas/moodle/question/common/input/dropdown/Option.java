package dev.rednas.moodle.question.common.input.dropdown;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Option {
    private String value;
    private String text;
    private boolean selected;
}

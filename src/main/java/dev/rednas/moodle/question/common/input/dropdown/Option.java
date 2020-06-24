package dev.rednas.moodle.question.common.input.dropdown;

import lombok.Data;

@Data
public class Option {
    private String value;
    private String text;
    private boolean selected;
}

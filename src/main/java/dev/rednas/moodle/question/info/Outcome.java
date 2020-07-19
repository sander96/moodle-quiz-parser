package dev.rednas.moodle.question.info;

import lombok.Data;
import pl.droidsonroids.jspoon.annotation.Selector;

@Data
public class Outcome {

    @Selector(value = "div.specificfeedback")
    private String specificFeedback;

    @Selector(value = "div.generalfeedback")
    private String generalFeedback;

    @Selector(value = "div.rightanswer")
    private String rightAnswer;
}

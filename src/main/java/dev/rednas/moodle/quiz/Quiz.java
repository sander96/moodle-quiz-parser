package dev.rednas.moodle.quiz;

import dev.rednas.moodle.question.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Quiz {
    List<Question> questions;
}

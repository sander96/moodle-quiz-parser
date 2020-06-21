package dev.rednas.moodle.quiz;

import dev.rednas.moodle.question.Question;
import lombok.Data;

import java.util.List;

@Data
public class Quiz {
    List<Question> questions;
}

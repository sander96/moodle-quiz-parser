package dev.rednas.moodle.language;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanguageUtilsTest {

    @Test
    void getIdentifier() {
        Optional<String> identifier = LanguageUtils.getIdentifier("Attempt finished submitting:");
        assertEquals(Optional.of("attemptfinishedsubmitting"), identifier);
    }

    @Test
    void getMultilineIdentifier() {
        String localizedString = "Imported grades must match one of the fixed list of valid grades - 100, 90, 80, 75, 70, 66.666, 60, 50, 40, 33.333, 30, 25, 20, 16.666, 14.2857, 12.5, 11.111, 10, 5, 0 (also negative values). If not, there are two options:\n" +
                "\n" +
                "*  Error if grade not listed - If a question contains any grades not found in the list an error is displayed and that question will not be imported\n" +
                "* Nearest grade if not listed - If a grade is found that does not match a value in the list, the grade is changed to the closest matching value in the list";

        Optional<String> identifier = LanguageUtils.getIdentifier(localizedString);
        assertEquals(Optional.of("matchgrades_help"), identifier);
    }
}

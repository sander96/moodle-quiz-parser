package dev.rednas.moodle.language;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    @Test
    void removeEscapeSlash() {
        String input = "I can\\'t believe you\\'ve done this."; // I can't believe you've done this.
        assertEquals("I can't believe you've done this.", RegexUtils.removeEscapeSlash(input));
    }
}

package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RegexUtils {

    public static String removeEscapeSlash(String string) {
        return string.replaceAll("\\\\'", "'");
    }
}

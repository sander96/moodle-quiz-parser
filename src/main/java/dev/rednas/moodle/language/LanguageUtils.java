package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageUtils {

    private static final Map<String, Map<String, String>> LOCALIZED_STRING_MAP = initialize();

    public static Optional<String> getIdentifier(String localizedString) {
        for (Map<String, String> languageValues : LOCALIZED_STRING_MAP.values()) {
            String identifier = languageValues.get(localizedString);
            if (identifier != null) {
                return Optional.of(identifier);
            }
        }
        return Optional.empty();
    }

    private static Map<String, Map<String, String>> initialize() {
        Map<String, Map<String, String>> map = new HashMap<>();
        List<String> languageFolders = ResourceUtils.getResourcesNames("/langpack");

        for (String language : languageFolders) {
            String path = "/langpack/" + language + "/question.php";
            String content = ResourceUtils.getResourceContent(path);
            Map<String, String> values = parseLocalizedStringsAndIdentifiers(content);
            map.put(language, values);
        }

        return map;
    }

    private static Map<String, String> parseLocalizedStringsAndIdentifiers(String fileContent) {
        String regexPattern = "\\$string\\['(.*)']\\s?=\\s?'([\\S\\s]*?)';";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.MULTILINE);

        Map<String, String> localizedStrings = new HashMap<>();

        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            localizedStrings.put(RegexUtils.removeEscapeSlash(matcher.group(2)), matcher.group(1));
        }
        return localizedStrings;
    }
}

package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageUtils {

    private static final Map<String, BidiMap<String, String>> LOCALIZED_STRING_MAP = initialize();

    public static Optional<String> getIdentifier(String localizedString) {
        for (BidiMap<String, String> languageValues : LOCALIZED_STRING_MAP.values()) {
            String identifier = languageValues.get(localizedString);
            if (identifier != null) {
                return Optional.of(identifier);
            }
        }
        return Optional.empty();
    }

    public static List<String> getOriginalLocalizedStrings(String identifier) {
        List<String> list = new ArrayList<>();
        for (BidiMap<String, String> languageValues : LOCALIZED_STRING_MAP.values()) {
            String originalLocalizedString = languageValues.getKey(identifier);
            if (originalLocalizedString != null) {
                list.add(originalLocalizedString);
            }
        }
        return list;
    }

    private static Map<String, BidiMap<String, String>> initialize() {
        Map<String, BidiMap<String, String>> map = new HashMap<>();
        List<String> languageFolders = ResourceUtils.getResourcesNames("/langpack");

        for (String language : languageFolders) {
            String path = "/langpack/" + language + "/question.php";
            String content = ResourceUtils.getResourceContent(path);
            BidiMap<String, String> values = parseLocalizedStringsAndIdentifiers(content);
            map.put(language, values);
        }

        return map;
    }

    private static BidiMap<String, String> parseLocalizedStringsAndIdentifiers(String fileContent) {
        String regexPattern = "\\$string\\['(.*)']\\s?=\\s?'([\\S\\s]*?)';";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.MULTILINE);

        BidiMap<String, String> localizedStrings = new DualHashBidiMap<>();

        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            localizedStrings.put(RegexUtils.removeEscapeSlash(matcher.group(2)), matcher.group(1));
        }
        return localizedStrings;
    }
}

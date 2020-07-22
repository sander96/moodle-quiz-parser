package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

        try {
            File[] languagePacksFolder = new File(LanguageUtils.class.getResource("/langpack").toURI()).listFiles();
            if (languagePacksFolder == null) {
                throw new RuntimeException("Not a folder");
            }

            for (File languageFolder : languagePacksFolder) {
                if (languageFolder.listFiles() == null) {
                    throw new RuntimeException("Not a folder");
                }

                for (File file : languageFolder.listFiles()) {
                    if (file.getName().equals("question.php")) {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        Map<String, String> values = parseLocalizedStringsAndIdentifiers(content);
                        map.put(languageFolder.getName(), values);
                    }
                }
            }

            return map;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
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

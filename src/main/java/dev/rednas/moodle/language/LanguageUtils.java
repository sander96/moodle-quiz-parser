package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageUtils {

    private static final Map<String, Map<String, String>> LOCALIZED_STRING_MAP = initialize();

    public static String getIdentifier(String localizedString) {
        for (Map<String, String> value : LOCALIZED_STRING_MAP.values()) {
            String identifier = value.get(localizedString);
            if (identifier != null) {
                return identifier;
            }
        }
        return null;
    }

    private static Map<String, Map<String, String>> initialize() {
        Map<String, Map<String, String>> map = new HashMap<>();

        try {
            File[] languagePacksFolder = new File(LanguageUtils.class.getResource("/langpack").toURI()).listFiles();
            if (languagePacksFolder == null) {
                throw new RuntimeException("Not a folder");
            }

            for (File file : languagePacksFolder) {
                File questionFile = new File(file, "question.php");

                String fileContent = new String(Files.readAllBytes(questionFile.toPath()));
                map.put(file.getName(), parseLocalizedStringsAndIdentifiers(fileContent));
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
            localizedStrings.put(matcher.group(2), matcher.group(1));
        }
        return localizedStrings;
    }
}

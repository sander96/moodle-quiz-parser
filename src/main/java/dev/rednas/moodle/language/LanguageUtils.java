package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageUtils {

    private static final Map<String, Map<LanguageComponent, Map<String, String>>> LOCALIZED_STRING_MAP = initialize();

    public static String getIdentifier(String localizedString, LanguageComponent component) {
        for (Map<LanguageComponent, Map<String, String>> languageValues : LOCALIZED_STRING_MAP.values()) {
            Map<String, String> languageMap = languageValues.get(component);

            String identifier = languageMap.get(localizedString);
            if (identifier != null) {
                return identifier;
            }
        }
        return null;
    }

    private static Map<String, Map<LanguageComponent, Map<String, String>>> initialize() {
        Map<String, Map<LanguageComponent, Map<String, String>>> map = new HashMap<>();

        try {
            File[] languagePacksFolder = new File(LanguageUtils.class.getResource("/langpack").toURI()).listFiles();
            if (languagePacksFolder == null) {
                throw new RuntimeException("Not a folder");
            }

            for (File languageFolder : languagePacksFolder) {
                if (languageFolder.listFiles() == null) {
                    throw new RuntimeException("Not a folder");
                }
                EnumMap<LanguageComponent, Map<String, String>> languageContent = new EnumMap<>(LanguageComponent.class);
                for (File file : languageFolder.listFiles()) {
                    String fileContent = new String(Files.readAllBytes(file.toPath()));
                    String filename = file.getName().split("\\.php")[0];
                    LanguageComponent component = LanguageComponent.valueOf(filename.toUpperCase());
                    languageContent.put(component, parseLocalizedStringsAndIdentifiers(fileContent));
                }
                map.put(languageFolder.getName(), languageContent);
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

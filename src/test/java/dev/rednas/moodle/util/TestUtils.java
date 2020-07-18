package dev.rednas.moodle.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    /**
     * Reads an HTML file from test resources folder.
     */
    public static String readHtml(String... filePath) {
        String[] htmlFilePath = concatenatePath(new String[]{"html"}, filePath);
        return getResource(htmlFilePath);
    }

    public static String getResource(String... filePath) {
        String resource = "/" + String.join("/", filePath);

        try {
            Path path = Paths.get(TestUtils.class.getResource(resource).toURI());
            return new String(Files.readAllBytes(path));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] concatenatePath(String[] path1, String[] path2) {
        return Stream.concat(Arrays.stream(path1), Arrays.stream(path2)).toArray(String[]::new);
    }
}

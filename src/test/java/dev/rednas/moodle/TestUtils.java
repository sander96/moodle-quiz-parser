package dev.rednas.moodle;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    /**
     * Reads an HTML file from test resources folder.
     */
    public static String readHtml(String filename) {
        try {
            Path path = Paths.get(TestUtils.class.getResource("/html/" + filename).toURI());
            return new String(Files.readAllBytes(path));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

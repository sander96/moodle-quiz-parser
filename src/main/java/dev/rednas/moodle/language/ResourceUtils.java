package dev.rednas.moodle.language;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ResourceUtils {

    public static List<String> getResourcesNames(String path) {
        try (InputStream resource = getResourceAsStream(path)) {
            return IOUtils.readLines(resource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InvalidResourceException(getFailedToReadResourceErrorMessage(path), e);
        }
    }

    public static String getResourceContent(String path) {
        try (InputStream resource = getResourceAsStream(path)) {
            return IOUtils.toString(resource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InvalidResourceException(getFailedToReadResourceErrorMessage(path), e);
        }
    }

    private static InputStream getResourceAsStream(String path) {
        InputStream resource = ResourceUtils.class.getResourceAsStream(path);
        if (resource == null) {
            throw new InvalidResourceException(getInvalidPathErrorMessage(path));
        }
        return resource;
    }

    private static String getInvalidPathErrorMessage(String path) {
        return String.format("Resource with path '%s' does not exist", path);
    }

    private static String getFailedToReadResourceErrorMessage(String path) {
        return String.format("Failed to read the resource with path: '%s'", path);
    }
}

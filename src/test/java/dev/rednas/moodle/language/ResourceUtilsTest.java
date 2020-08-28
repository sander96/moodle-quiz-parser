package dev.rednas.moodle.language;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceUtilsTest {
    private final static String FOLDER_PATH = "/test";
    private final static String FILE_PATH = "/test/folder1/file.txt";

    @Test
    void getResourceNames() {
        List<String> resources = ResourceUtils.getResourcesNames(FOLDER_PATH);
        resources.sort(String::compareTo);
        assertEquals(List.of("folder1", "folder2"), resources);
    }

    @Test
    void getResourceNamesInvalidPath() {
        InvalidResourceException exception = assertThrows(InvalidResourceException.class,
                () -> ResourceUtils.getResourcesNames("invalid-path"));
        assertEquals("Resource with path 'invalid-path' does not exist", exception.getMessage());
    }

    @Test
    void getResourceNamesIOException() throws IOException {
        try (MockedStatic<IOUtils> ignored = Mockito.mockStatic(IOUtils.class)) {
            Mockito.when(IOUtils.readLines(Mockito.any(InputStream.class), Mockito.eq(StandardCharsets.UTF_8)))
                    .thenThrow(new IOException());
            InvalidResourceException exception = assertThrows(InvalidResourceException.class,
                    () -> ResourceUtils.getResourcesNames(FOLDER_PATH));
            assertEquals("Failed to read the resource with path: '/test'", exception.getMessage());
        }
    }

    @Test
    void getResourceContent() {
        String content = ResourceUtils.getResourceContent(FILE_PATH);
        assertEquals("line1\nline2\n", content);
    }

    @Test
    void getResourceContentInvalidPath() {
        InvalidResourceException exception = assertThrows(InvalidResourceException.class,
                () -> ResourceUtils.getResourcesNames("/test/folder1/invalid"));
        assertEquals("Resource with path '/test/folder1/invalid' does not exist", exception.getMessage());
    }


    @Test
    void getResourceContentIOException() throws IOException {
        try (MockedStatic<IOUtils> ignored = Mockito.mockStatic(IOUtils.class)) {
            Mockito.when(IOUtils.toString(Mockito.any(InputStream.class), Mockito.eq(StandardCharsets.UTF_8)))
                    .thenThrow(new IOException());
            InvalidResourceException exception = assertThrows(InvalidResourceException.class,
                    () -> ResourceUtils.getResourceContent(FILE_PATH));
            assertEquals("Failed to read the resource with path: '/test/folder1/file.txt'", exception.getMessage());
        }
    }
}

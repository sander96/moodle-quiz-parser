package dev.rednas.moodle.language;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceUtilsTest {

    @Test
    void getResourceNames() {
        List<String> resources = ResourceUtils.getResourcesNames("/test");
        resources.sort(String::compareTo);
        assertEquals(List.of("folder1", "folder2"), resources);
    }

    @Test
    void getResourceNamesInvalidPath() {
        assertThrows(InvalidResourceException.class, () -> ResourceUtils.getResourcesNames("invalid-path"));
    }

    @Test
    void getResourceContent() {
        String content = ResourceUtils.getResourceContent("/test/folder1/file.txt");
        assertEquals("line1\nline2\n", content);
    }

    @Test
    void getResourceContentInvalidPath() {
        assertThrows(InvalidResourceException.class, () -> ResourceUtils.getResourcesNames("/test/folder1/invalid"));
    }
}

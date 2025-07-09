package storage;

import org.junit.jupiter.api.*;

import model.Task;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileStorageTest {

    private static final String TEST_FILE_PATH = "tasks_test.json";
    private FileStorage fileStorage;

    @BeforeEach
    void setUp() {
        fileStorage = new FileStorage(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadTasks() {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Test zadanie 1", false));
        tasks.add(new Task(2, "Test zadanie 2", true));

        // When
        fileStorage.saveTasks(tasks);
        List<Task> loadedTasks = fileStorage.loadTasks();

        // Then
        assertEquals(2, loadedTasks.size());
        assertEquals("Test zadanie 1", loadedTasks.get(0).getTitle());
        assertTrue(loadedTasks.get(1).isDone());
    }

    @Test
    void testLoadFromEmptyFileReturnsEmptyList() throws Exception {
        // Given
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();

        // When
        List<Task> tasks = fileStorage.loadTasks();

        // Then
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    void testLoadFromNonexistentFileReturnsEmptyList() {
        // Given — plik nie istnieje

        // When
        List<Task> tasks = fileStorage.loadTasks();

        // Then
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }
}

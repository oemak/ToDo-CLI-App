package storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Task;

public class FileStorage {
    private String filePath;
    private ObjectMapper objectMapper;

    public FileStorage(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    public List<Task> loadTasks() {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " +  e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }
}

package src.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean done;
    private LocalDate dueDate;

    public Task(int id, String title, String description, boolean done, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}

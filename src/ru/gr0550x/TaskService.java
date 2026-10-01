package ru.gr0550x;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public void createTask(String title, String deadlineText, String priorityText){
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Название задачи не может быть пустым");
        LocalDate deadline = parseDeadline(deadlineText);
        Priority priority = parsePriority(priorityText);

        tasks.add(new Task(title, deadline, priority));
    }

    public List<Task> getAllTasks(){
        return List.copyOf(tasks);
    }

    private LocalDate parseDeadline(String deadlineText){
        if (deadlineText == null || deadlineText.isBlank())
            throw new IllegalArgumentException("Дата выполнения не может быть пустой");

        try {
            LocalDate deadline = LocalDate.parse(deadlineText.trim());

            if (deadline.isBefore(LocalDate.now()))
                throw new IllegalArgumentException("Дедлайн не может быть в прошлом");

            return deadline;
        } catch (DateTimeParseException ex){
            throw new IllegalArgumentException("Дата должна быть в формате yyyy-MM-dd", ex);
        }
    }

    private Priority parsePriority(String priorityText){
        if (priorityText == null || priorityText.isBlank())
            return Priority.MEDIUM;
        try {
            return Priority.valueOf(priorityText.trim().toUpperCase());
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Приоритет должен быть LOW, MEDIUM или HIGH", ex);
        }
    }
}

package ru.gr0550x;

import java.util.List;

public interface TaskFormView {
    String getTaskTitle();
    String getDeadline();
    String getPriority();

    void clearForm();
    void showTasks(List<Task> tasks);
    void render(TaskFormState state);
}

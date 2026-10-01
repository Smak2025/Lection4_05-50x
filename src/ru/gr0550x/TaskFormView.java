package ru.gr0550x;

import java.util.List;

public interface TaskFormView {
    String getTitle();
    String getDeadline();
    String getPriority();

    void clearForm();
    void showTask(List<Task> tasks);
    void render(TaskFormState state);
}

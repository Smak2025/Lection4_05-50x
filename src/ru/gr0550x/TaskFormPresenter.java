package ru.gr0550x;

public class TaskFormPresenter {
    private final TaskService service;
    private TaskFormView view;

    public TaskFormPresenter(TaskService service){
        this.service = service;
    }

    public void attachView(TaskFormView view){
        this.view = view;
    }

    public void saveClicked(){
        try {
            service.createTask(
                    view.getTitle(),
                    view.getDeadline(),
                    view.getPriority()
            );
            var tasks = service.getAllTasks();
            view.showTask(tasks);
            view.clearForm();
            view.render(TaskFormState.setSaved(tasks.size()));
        } catch (IllegalArgumentException ex){
            view.render(TaskFormState.setError(ex.getMessage()));
        }
    }
}

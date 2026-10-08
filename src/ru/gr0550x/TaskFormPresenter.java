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
                    view.getTaskTitle(),
                    view.getDeadline(),
                    view.getPriority()
            );
            var tasks = service.getAllTasks();
            view.showTasks(tasks);
            view.clearForm();
            view.render(TaskFormState.setSaved(tasks.size()));
        } catch (IllegalArgumentException ex){
            view.render(TaskFormState.setError(ex.getMessage()));
        }
    }

    public void viewOpened(){
        view.showTasks(service.getAllTasks());
        view.render(TaskFormState.init());
    }

    public void inputChanged(){
        if (isSaveAllowed()){
            view.render(TaskFormState.setReady());
        } else {
            view.render(TaskFormState.init());
        }
    }

    public boolean isSaveAllowed(){
        return isNotBlank(view.getTaskTitle())
                && isNotBlank(view.getDeadline());
    }

    private boolean isNotBlank(String value){
        return value != null && !value.isBlank();
    }
}

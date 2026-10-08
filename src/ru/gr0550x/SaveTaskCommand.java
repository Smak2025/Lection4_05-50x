package ru.gr0550x;

public class SaveTaskCommand implements Command {
    private final TaskFormPresenter presenter;

    public SaveTaskCommand(TaskFormPresenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void execute() {
        if (canExecute()) {
            presenter.saveClicked();
        }
    }

    @Override
    public boolean canExecute() {
        return presenter.isSaveAllowed();
    }
}

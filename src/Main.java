import ru.gr0550x.TaskFormPresenter;
import ru.gr0550x.TaskService;
import ru.gr0550x.TaskWindow;

void main() {
    TaskService service = new TaskService();

    TaskFormPresenter presenter = new TaskFormPresenter(service);
    TaskWindow window = new TaskWindow(presenter);
    presenter.attachView(window);

    window.setVisible(true);
    presenter.viewOpened();
}
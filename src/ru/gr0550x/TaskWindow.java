package ru.gr0550x;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaskWindow extends JFrame implements TaskFormView {

    private final TaskFormPresenter presenter;

    private final JTextField titleField = new JTextField(20);
    private final JTextField deadlineField = new JTextField(20);
    private final JTextField priorityField = new JTextField(20);
    private final JButton saveButton = new JButton("Сохранить задачу");
    private final JLabel statusLabel = new JLabel();
    private final JTextArea tasksArea = new JTextArea(12, 45);

    private final Command saveCommand;

    public TaskWindow(TaskFormPresenter presenter){
        this.presenter = presenter;

        saveCommand = new SaveTaskCommand(presenter);

        setTitle("Задачи");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Текст (название) задачи: "));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Дедлайн yyyy-MM-dd:"));
        inputPanel.add(deadlineField);

        inputPanel.add(new JLabel("Приоритет:"));
        inputPanel.add(priorityField);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(saveButton, BorderLayout.SOUTH);

        tasksArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tasksArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            saveCommand.execute();
        });

        titleField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                presenter.inputChanged();
            }
        });

        deadlineField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                presenter.inputChanged();
            }
        });

        titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                presenter.inputChanged();
            }
        });

        deadlineField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                 presenter.inputChanged();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public String getTaskTitle() {
        return titleField.getText();
    }

    @Override
    public String getDeadline() {
        return deadlineField.getText();
    }

    @Override
    public String getPriority() {
        return priorityField.getText();
    }

    @Override
    public void clearForm() {
        titleField.setText("");
        deadlineField.setText("");
        priorityField.setText("");
    }

    @Override
    public void showTasks(List<Task> tasks) {
        tasksArea.setText("");

        for(var task: tasks){
            tasksArea.append(task.title());
            tasksArea.append(" | ");
            tasksArea.append(task.deadline().toString());
            tasksArea.append(" | ");
            tasksArea.append(task.priority().toString());
            tasksArea.append(System.lineSeparator());
        }
    }

    @Override
    public void render(TaskFormState state) {
        saveButton.setEnabled(state.saveEnabled());
        statusLabel.setText(state.status());

        if (state.error() != null){
            JOptionPane.showMessageDialog(this, state.error());
        }
    }
}
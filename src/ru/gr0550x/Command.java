package ru.gr0550x;

public interface Command {
    void execute();
    boolean canExecute();
}

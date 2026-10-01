package ru.gr0550x;

public record TaskFormState(
        boolean saveEnabled,
        String status,
        String error
) {
    public static TaskFormState init(){
        return new TaskFormState(false, "Заполните форму", null);
    }

    public static TaskFormState setReady(){
        return new TaskFormState(true, "Задачу можно сохранить", null);
    }

    public static TaskFormState setSaved(int taskCount){
        return new TaskFormState(
                false,
                "Задача сохранена. Всего задач: " + taskCount,
                null
                );
    }

    public static TaskFormState setError(String message){
        return new TaskFormState(false, "Ошибка", message);
    }
}

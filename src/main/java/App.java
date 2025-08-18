public class App {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Simple Task 1","simple task1", Status.NEW);
        Task task2 = new Task("Simple Task 2","simple task2", Status.NEW);
        Task task3 = new Task("Simple Task 3","simple task3", Status.NEW);
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);
        taskManager.getAllTasks();
    }
}

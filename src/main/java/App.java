public class App {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("task1", "Первая простая задача", Status.NEW);
        int task1Id = taskManager.createTask(task1);

        Epic epic1 = new Epic("epic1","epic1");
        int epic1Id = taskManager.createTask(epic1);
        Epic epic2 = new Epic("epic2","epic2");
        int epic2Id = taskManager.createTask(epic2);

        SubTask subtask1 = new SubTask("subtask1","subtask1", Status.IN_PROGRESS, epic1);
        int subtask1Id = taskManager.createTask(subtask1);
        SubTask subtask2 = new SubTask("subtask2","subtask2", Status.NEW, epic1);
        int subtask2Id = taskManager.createTask(subtask2);
        SubTask subtask3 = new SubTask("subtask2","subtask3", Status.NEW, epic1);
        int subtask3Id = taskManager.createTask(subtask3);
        taskManager.getAllTasks();

        SubTask updateTask1 = new SubTask(subtask1Id,"upd subtask1","subtask1", Status.IN_PROGRESS, epic2);
        taskManager.updateTask(updateTask1);
        taskManager.getAllTasks();
        taskManager.removeTaskById(subtask1Id);
        taskManager.getAllTasks();
        taskManager.removeTaskById(epic1Id);
        taskManager.getAllTasks();
    }
}

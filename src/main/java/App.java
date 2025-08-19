public class App {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1 простая", "Первая простая задача",Status.NEW);
        Task task2 = new Task("2 простая", "Вторая простая задача",Status.NEW);
        Epic epic1 = new Epic("1 эпик", "1 эпик");
        Epic epic2 = new Epic("2 эпик", "2 эпик");
        SubTask subTask1 = new SubTask("1 подзадача 1 эпика","1 подзадача", Status.NEW, epic1);
        SubTask subTask2 = new SubTask("2 подзадача 1 эпика","2 подзадача", Status.NEW, epic1);
        SubTask subTask3 = new SubTask("1 подзадача 2 эпика","3 подзадача", Status.NEW, epic2);
        taskManager.createTaskAuto(task1,task2,epic1,epic2,subTask1,subTask2,subTask3);
        taskManager.getAllSubTasks(epic1.getId());
        taskManager.getAllSubTasks(epic2.getId());
        Task task2Upd = new Task("2 простая updated", "Вторая простая задача",Status.IN_PROGRESS);
        Epic epic2Upd = new Epic("2 эпик updated", "2 эпик");
        SubTask subTask1Upd = new SubTask("1 подзадача 1 эпика updated","1 подзадача", Status.IN_PROGRESS, epic2);
        SubTask subTask2Upd = new SubTask("2 подзадача 1 эпика updated","2 подзадача", Status.NEW, epic1);
        SubTask subTask3Upd = new SubTask("1 подзадача 2 эпика updated","3 подзадача", Status.NEW, epic2);
        taskManager.updateTask(subTask3.getId(), subTask3Upd);
        System.out.println("----------------------");
        taskManager.getAllSubTasks(epic1.getId());
        taskManager.getAllSubTasks(epic2.getId());
    }
}

public class App {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        
        Epic epicTask1 = new Epic("Epic Task 1","EPIC TASK 1");
        taskManager.createTask(epicTask1);
        SubTask subTask1 = new SubTask("subTask 1","sub task 1",Status.DONE,epicTask1.getId());
        SubTask subTask2 = new SubTask("subTask 2","sub task 2",Status.DONE,epicTask1.getId());
        taskManager.createTask(subTask1);
        taskManager.createTask(subTask2);
        taskManager.getAllSubTasks(epicTask1.getId());
    }
}

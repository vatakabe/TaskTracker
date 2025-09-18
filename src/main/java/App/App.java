package App;

import App.History.*;
import App.Tasks.*;

public class App {
    public static void main(String[] args) {


        TaskManager inMemoryTaskManager = Managers.getDefault();
        Task task1 = new Task("task1", "Первая простая задача", Status.NEW);
        int task1Id = inMemoryTaskManager.createTask(task1);
//        Epic epic1 = new Epic("epic1","epic1");
//        int epic1Id = inMemoryTaskManager.createTask(epic1);
//        Epic epic2 = new Epic("epic2","epic2");
//        int epic2Id = inMemoryTaskManager.createTask(epic2);
//
//        SubTask subtask1 = new SubTask("subtask1","subtask1", Status.IN_PROGRESS, epic1);
//        int subtask1Id = inMemoryTaskManager.createTask(subtask1);
//        SubTask subtask2 = new SubTask("subtask2","subtask2", Status.NEW, epic1);
//        int subtask2Id = inMemoryTaskManager.createTask(subtask2);
//        SubTask subtask3 = new SubTask("subtask2","subtask3", Status.NEW, epic1);
//        int subtask3Id = inMemoryTaskManager.createTask(subtask3);
//        SubTask updateTask1 = new SubTask(subtask1Id,"upd subtask1","subtask1", Status.IN_PROGRESS, epic2);
//        inMemoryTaskManager.updateTask(updateTask1);
//        inMemoryTaskManager.getAllTasks().values().forEach(System.out::println);
            inMemoryTaskManager.getHistory().forEach(System.out::println);
    }
}

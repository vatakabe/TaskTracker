package App;

import App.History.*;
import App.Tasks.*;

public class App {
    public static void main(String[] args) {


        TaskManager inMemoryTaskManager = Managers.getDefault();
//        Task task1 = new Task("task1", "Первая простая задача", Status.NEW);
//        int task1Id = inMemoryTaskManager.createTask(task1);
//        Task task2 = new Task("task2", "Первая простая задача", Status.NEW);
//        int task2Id = inMemoryTaskManager.createTask(task2);
//        Task task3 = new Task("task3", "Первая простая задача", Status.NEW);
//        int task3Id = inMemoryTaskManager.createTask(task3);
//        Task task4 = new Task("task4", "Первая простая задача", Status.NEW);
//        int task4Id = inMemoryTaskManager.createTask(task4);
//        Task task5 = new Task("task5", "Первая простая задача", Status.NEW);
//        int task5Id = inMemoryTaskManager.createTask(task5);
//        Task task6 = new Task("task6", "Первая простая задача", Status.NEW);
//        int task6Id = inMemoryTaskManager.createTask(task6);
//
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
//        inMemoryTaskManager.getTask(subtask3Id);
//        inMemoryTaskManager.getTask(epic1Id);
//        inMemoryTaskManager.getTask(epic2Id);

        Task task = new Task("task24", "sadasfg", Status.NEW);
        int taskId = inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.getAllTasks().values().forEach(System.out::println);

    }
}

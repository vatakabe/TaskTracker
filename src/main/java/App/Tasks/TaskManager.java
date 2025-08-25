package App.Tasks;

import App.History.HistoryManager;
import App.History.*;
import java.util.*;

public interface TaskManager {
    HistoryManager getHistoryManager();
    void getAllTasks();
    Task getTask(int id);
    Epic getEpic(int id);
    SubTask getSubTask(int id);

    void deleteAllTasks();

    Task getTaskById(int id);

    int createTask(Task task);

    void updateTask(Task task);

    void removeTaskById(int id);

    List<SubTask> getAllSubTasksById(int id);

}

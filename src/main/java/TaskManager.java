import java.util.*;

public interface TaskManager {
    void getAllTasks();

    void deleteAllTasks();

    Task getTaskById(int id);

    int createTask(Task task);

    void updateTask(Task task);

    void removeTaskById(int id);

    List<SubTask> getAllSubTasksById(int id);
}

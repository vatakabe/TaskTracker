import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static int idCounter = 1;
    Map<Integer, Task> taskMap = new HashMap<>();
    public void getAllTasks(){
        for(Map.Entry<Integer,Task> task: taskMap.entrySet()){
            System.out.println(task.getValue());
        }
    }


    public void deleteAllTasks() {
        taskMap.clear();
    }

    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    public void createTask(Task task) {
        task.setId(idCounter++);
        taskMap.put(task.getId(),task);
    }
    public void updateTask(int id, Task task) {
        taskMap.put(id, task);
    }

    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void getAllSubTasks(int id) {
    }
}

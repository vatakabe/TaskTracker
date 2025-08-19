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

    public static int getUnicalId(){
        return idCounter++;
    }
    public void deleteAllTasks() {
        taskMap.clear();
    }

    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    public void createTask(Task task) {
        task.setId(getUnicalId());
        taskMap.put(task.getId(),task);

        if( task instanceof SubTask subTask){
            subTask.setCurrentEpic(subTask.getCurrentEpic());
        }

    }
    public void createTaskAuto(Task...tasks) {
        for( Task task: tasks){
            createTask(task);
        }
    }
    public void updateTask(int id, Task task) {
        Task updatingTask = taskMap.get(id);
        if(updatingTask instanceof SubTask subTask){
            subTask.update(task);
        }
        else{
            task.update(task);
        }
    }

    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void getAllSubTasks(int id) {
        Epic epic =(Epic) taskMap.get(id);
        System.out.println(epic);
        for( SubTask subTask: epic.getSubTaskList().values() ){
            System.out.println(subTask);
        }
    }
}

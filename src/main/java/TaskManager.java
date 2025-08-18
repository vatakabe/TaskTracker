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

        if( task instanceof SubTask subTask){
            Epic epic =  (Epic) taskMap.get( subTask.getEpicId() );
            epic.addSubTask(subTask);
        }


    }
    public void updateTask(int id, Task task) {

        taskMap.put(id, task);
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Epic extends Task{
    private Map<Integer,SubTask> subTaskList = new HashMap<>();

    public Epic(String name, String description, Status status) {
        super.setName(name);
        super.setDescription(description);
        super.setStatus(status);
    }
    public void updateEpicTaskStatus(){

    }
    public void addSubTask(Task task){
        updateEpicTaskStatus();
    }
    public void deleteSubTask(int id){
        updateEpicTaskStatus();
    }

}

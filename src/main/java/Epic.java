import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Epic extends Task{
    private Map<Integer,SubTask> subTaskList = new HashMap<>();
    private List<Status> statusList = new ArrayList<>();
    public Epic(String name, String description) {
        super(name, description, Status.NEW);
    }
    public void updateEpicTaskStatus(){
        statusList.clear();
        if( subTaskList.isEmpty()) {
            super.setStatus(Status.NEW);
            return;
        }
        for( SubTask subTask: subTaskList.values()){
            statusList.add( subTask.getStatus() );
        }
        if( ( statusList.contains(Status.NEW) && statusList.contains(Status.DONE))
                || statusList.contains(Status.IN_PROGRESS)) {
            super.setStatus(Status.IN_PROGRESS);
        }else if( statusList.contains(Status.DONE) ){
            super.setStatus(Status.DONE);
        }else{
            super.setStatus(Status.NEW);
        }
    }

    public void addSubTask(Task task){
        subTaskList.put(task.getId(), (SubTask) task);
        updateEpicTaskStatus();
    }
    public void deleteSubTask(Task task){
        subTaskList.remove(task.getId());
        updateEpicTaskStatus();
    }

    public Map<Integer,SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(Map<Integer,SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }
}

package App.Tasks;

import App.Status;
import App.History.*;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasksIds = new ArrayList<>();
    public Epic(String name, String description) {
        super(name, description, Status.NEW);
    }
    public Epic(int id, String name, String description, Status status){
        super(id,name,description,status);
    }
    public List<Integer> getSubTasksIds(){
        return  subTasksIds;
    }
    public void setSubTasksIds(List<Integer> subTasksIds){
        this.subTasksIds = subTasksIds;
    }

}

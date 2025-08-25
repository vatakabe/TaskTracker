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
    public List<Integer> getSubTasksIds(){
        return  subTasksIds;
    }
    public void setSubTasksIds(List<Integer> subTasksIds){
        this.subTasksIds = subTasksIds;
    }

    @Override
    public String toString(){
        return String.format("id = %s; name = %s; status = %s,type = %s\n" +
                        "subtasks ids %s",
                getId(),getName(),getStatus(), getClass(), subTasksIds.toString());
    }
}

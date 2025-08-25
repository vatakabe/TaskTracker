package App.Tasks;

import App.Status;
import App.History.*;
public class SubTask extends Task {


    private Integer epicId;
    public SubTask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        epicId = epic.getId();
    }
    public SubTask(int id,String name, String description, Status status, Epic epic) {
        super(id,name, description, status);
        epicId = epic.getId();
    }
    public void setEpicId(Integer epicId){
        this.epicId = epicId;
    }
    public Integer getEpicId(){
        return epicId;
    }
}

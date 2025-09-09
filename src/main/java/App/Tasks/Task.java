package App.Tasks;

import App.Status;
import App.History.*;
import App.Types;

import java.util.Objects;

public class Task {
    private Integer id;
    private String name;
    private Status status;
    private String description;
    private Types type;
    {
        String strType = this.getClass().getSimpleName();
        type = Types.valueOf(strType.toUpperCase());
    }
    public Task() {

    }
    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
    public Task(int id, String name, String description, Status status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Types getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString(){
        String result = String.format(
                "%s,%s,%s,%s,%s,\n",
                getId(),getType(),getName(),getStatus(),getDescription()
        );
        return result;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private List<SubTask> subTaskList = new ArrayList<>();

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
    }
}

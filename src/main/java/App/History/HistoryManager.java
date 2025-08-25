package App.History;

import App.Tasks.*;

import java.util.List;

public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
}

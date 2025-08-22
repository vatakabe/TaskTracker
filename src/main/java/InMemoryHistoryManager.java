import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private List<Task>historyList = new ArrayList<>();
    private static final int HISTORY_MEMORY = 10;
    @Override
    public void add(Task task) {
        if(historyList.size() < HISTORY_MEMORY){
            historyList.add(task);
        }else{
            historyList.removeFirst();
            historyList.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}

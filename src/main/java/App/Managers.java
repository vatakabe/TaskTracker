package App;

import App.History.*;
import App.Tasks.*;

public class Managers {

    public static TaskManager getDefault(){
        return new FileBackedTaskManager("D:\\dev\\taskTest.txt");
    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}

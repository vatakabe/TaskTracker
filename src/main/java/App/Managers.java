package App;

import App.History.*;
import App.Tasks.*;

public class Managers {

    public static TaskManager getDefault(){
        return new FileBackedTaskManager("C:\\temp\\taskTest.txt");
    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}

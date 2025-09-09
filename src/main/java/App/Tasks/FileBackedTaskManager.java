package App.Tasks;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager{
    private final String filePath;

    public FileBackedTaskManager(String strPath){
        super();
        this.filePath = strPath;
    }
    public void save(){
        Map<Integer,Task> saveTaskMap = super.getAllTasks();
        try(BufferedWriter bos = new BufferedWriter(new FileWriter(filePath,false))){
            String line = "id,type,name,status,description,epic\n";
            bos.write(line);
            for(Task task: saveTaskMap.values()){
                bos.write(task.toString());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int createTask(Task task) {
        int returnId =  super.createTask(task);
        save();
        return returnId;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void removeTaskById(int id) {
        super.removeTaskById(id);
        save();
    }
}

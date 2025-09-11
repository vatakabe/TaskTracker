package App.Tasks;

import App.Status;
import App.Types;

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
    public void loadFromFile(){

    }
    private void createFromFile(String line) throws IOException{
        String[] args = line.split(",");
        Types taskType = Types.valueOf(args[2].toUpperCase());
        //task parameters
        int id = Integer.valueOf(args[0]);
        String name = args[2];
        Status status = Status.valueOf(args[3].toUpperCase());
        String description = args[4];
        int epicId = Integer.valueOf(args[5]);
        switch (taskType){
            case EPIC -> {
                Epic epicTask = new Epic(id,name,description,status);
                super.createTask(epicTask);
            }
            case SUBTASK -> {
                Epic epic = (Epic) super.getAllTasks().get(epicId);
                SubTask subTask = new SubTask(id,name,description,status,epic);
                super.createTask(subTask);
            }
            case TASK -> {
                Task task = new Task(id,name,description,status);
                super.createTask(task);
            }
            default -> {
                throw new IOException("Задача из файла не подходит");
            }
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

package App.Tasks;

import App.History.HistoryManager;
import App.Status;
import App.Types;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class FileBackedTaskManager extends InMemoryTaskManager{
    private final String filePath;

    public FileBackedTaskManager(String strPath){
        super();
        this.filePath = strPath;
        loadFromFile();
        //loadHistoryFromFile();
    }

    private void loadHistoryFromFile() {
        try( BufferedReader br = new BufferedReader(new FileReader(filePath)) ){
            while(br.ready()){
                String line = br.readLine();
                if( line.equals("#History")){

                }
            }
            updateEpics();
            updateIdCounter();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void save(){
        Map<Integer,Task> saveTaskMap = super.getAllTasks();
        try(BufferedWriter bos = new BufferedWriter(new FileWriter(filePath,false))){
            String line = "id,type,name,status,description,epic\n";
            bos.write(line);
            for(Task task: saveTaskMap.values()){
                bos.write(task.toString());
            }

            List<Task> history =super.getHistory();
            if(!history.isEmpty()){
                bos.write("#history\n");
                StringBuilder sb = new StringBuilder();
                for(Task value: history){
                    sb.append(value.getId()+",");
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }
                bos.write(sb.toString());
                bos.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadFromFile(){
        try( BufferedReader br = new BufferedReader(new FileReader(filePath)) ){
            String header = br.readLine();
            if(header == null){
                System.out.println("file is empty");
                return;
            }
            while(br.ready()){
                String line = br.readLine();
                if(line.equalsIgnoreCase("#history")) break;
                try{
                    Task task = fromString(line);
                    getAllTasks().put(task.getId(), task);
                }
                catch(RuntimeException e){
                    break;
                }
            }
            updateEpics();
            updateIdCounter();

            String historyLine = br.readLine();
            if(historyLine !=null && !historyLine.trim().isEmpty()){
                String[] history = historyLine.split(",");
                HistoryManager historyManager = getHistoryManager();
                Map<Integer, Task> taskMap = getAllTasks();
                for(String taskId: history){
                    try{
                        Task task = taskMap.get(Integer.parseInt(taskId));
                        if(task != null){
                            historyManager.add(task);
                        }
                    }
                    catch( NumberFormatException e){
                        System.out.println("Некорректный id истории");
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private Task fromString(String line){
        String[] args = line.split(",");
        //task parameters
        int id = Integer.valueOf(args[0]);
        Types taskType = Types.valueOf(args[1].toUpperCase());
        String name = args[2];
        Status status = Status.valueOf(args[3].toUpperCase());
        String description = args[4];
        switch (taskType){
            case EPIC -> {
                Epic epicTask = new Epic(id,name,description,status);
                return epicTask;
            }
            case SUBTASK -> {
                int epicId = Integer.valueOf(args[5]);
                Epic epic = (Epic) super.getAllTasks().get(epicId);
                SubTask subTask = new SubTask(id,name,description,status,epic);
                return subTask;
            }
            case TASK -> {
                Task task = new Task(id,name,description,status);
                return task;
            }
            default -> {
                throw new RuntimeException();
            }
        }

    }
    private void updateIdCounter() {
        Integer maxId = getAllTasks().keySet().stream().max(Comparator.comparingInt(o -> o)).get();
        setIdCounter(maxId);
    }

    private void updateEpics() {
        Map<Integer,Task> taskMap = getAllTasks();
        for( Task task: getAllTasks().values() ){
            if(task instanceof SubTask subTask){
                Integer epicId = subTask.getEpicId();
                if( !taskMap.containsKey(epicId)){
                    System.out.println("Ошибка! Для задачи "+ subTask.getName() +" эпика не существует!");
                    throw new IllegalArgumentException();
                }
                Epic epic =(Epic) taskMap.get(epicId);
                List<Integer> subtaskIds = epic.getSubTasksIds();
                subtaskIds.add(subTask.getId());
                super.updateEpicTaskStatus(epic);
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

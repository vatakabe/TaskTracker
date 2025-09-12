package App.Tasks;

import App.*;
import App.History.HistoryManager;
import App.History.*;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {


    private static Integer idCounter;
    private HistoryManager historyManager;
    private Map<Integer, Task> taskMap;
    static{
        idCounter = 1;
    }
    {
        taskMap = new HashMap<>();
    }
    public InMemoryTaskManager(){
        historyManager = Managers.getDefaultHistory();
    }

    public void getHistory(){
        historyManager.getHistory().forEach(System.out::println);
    }
    @Override
    public Map<Integer,Task> getAllTasks() {
        //Map<Integer,Task> result = new HashMap<>(taskMap);
        return taskMap;
    }

    public static void setIdCounter(Integer idCounter) {
        InMemoryTaskManager.idCounter = idCounter;
    }
    @Override
    public Task getTask(int id) {
        if(!taskMap.containsKey(id)){
            System.out.println("Задачи с таким id не существует!");
            throw new IllegalArgumentException();
        }
        historyManager.add(taskMap.get(id));
        return taskMap.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        if(!taskMap.containsKey(id)){
            System.out.println("Задачи с таким id не существует!");
            throw new IllegalArgumentException();
        }
        if(taskMap.get(id) instanceof Epic epic){
            historyManager.add(taskMap.get(id));
            return epic;
        }else{
            System.out.println("переданный id ссылается не на эпик!");
            throw new IllegalStateException();
        }
    }

    @Override
    public SubTask getSubTask(int id) {
        if(!taskMap.containsKey(id)){
            System.out.println("Задачи с таким id не существует!");
            throw new IllegalArgumentException();
        }
        if(taskMap.get(id) instanceof SubTask subTask){
            historyManager.add(taskMap.get(id));
            return subTask;
        }else{
            System.out.println("переданный id ссылается не на подзадачу!");
            throw new IllegalStateException();
        }
    }

    private static Integer generateUnicalId() {
        return idCounter++;
    }
    @Override
    public void deleteAllTasks() {
        taskMap.clear();
    }
    @Override
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }
    @Override
    public int createTask(Task task) {
        if( taskMap.containsKey(task.getId()) ){
            System.out.println("Такая задача уже существует!");
            throw new IllegalArgumentException();
        }
        if( task instanceof SubTask subTask){
            Integer epicId = subTask.getEpicId();
            if( !taskMap.containsKey(epicId)){
                System.out.println("Ошибка! Для задачи "+ subTask.getName() +" эпика не существует!");
                throw new IllegalArgumentException();
            }
            subTask.setId(generateUnicalId());
            taskMap.put(subTask.getId(),subTask);
            Epic epic =(Epic) taskMap.get(epicId);
            List<Integer> subtaskIds = epic.getSubTasksIds();
            subtaskIds.add(subTask.getId());
            updateEpicTaskStatus(epic);
            return subTask.getId();
        }else{
            task.setId(generateUnicalId());
            taskMap.put(task.getId(),task);
            return task.getId();
        }
    }
    @Override
    public void updateTask(Task task) {
        if( task.getId() == null ){
            System.out.println("Ошибка! Задача передана c пустым id");
            return;
        }
        if( !taskMap.containsKey(task.getId()) ){
            System.out.println("Такой задачи не существует!");
            return;
        }
        if( task instanceof SubTask subTask){
            //Проводки по сабтаску
            Integer epicId = subTask.getEpicId();
            if( !taskMap.containsKey(epicId)){
                System.out.println("Ошибка! Для задачи "+ subTask.getName() +" эпика не существует!");
                return;
            }
            //если ИД эпика не совпадают в новом и старом варианте
            SubTask oldSubTask =(SubTask) taskMap.get(subTask.getId());
            if(!Objects.equals(oldSubTask.getEpicId(), subTask.getEpicId())){
                Epic oldEpicSubtask =(Epic) taskMap.get(oldSubTask.getEpicId());
                oldEpicSubtask.getSubTasksIds().remove(oldSubTask.getId());
                updateEpicTaskStatus(oldEpicSubtask);
            }
            taskMap.put(subTask.getId(),subTask);
            Epic epic =(Epic) taskMap.get(subTask.getEpicId());
            epic.getSubTasksIds().add(subTask.getId());
            updateEpicTaskStatus(epic);

        }else if(task instanceof Epic newEpic){
            //Сохраняем старый список подзадач в обновленный эпик
            Epic oldEpic =(Epic) taskMap.get(newEpic.getId());
            List<Integer> OldSubTasksIds = oldEpic.getSubTasksIds();
            newEpic.setSubTasksIds(OldSubTasksIds);
            taskMap.put(newEpic.getId(),newEpic);
        }
        else{// по умолчанию
            taskMap.put(task.getId(),task);
        }
    }
    @Override
    public void removeTaskById(int id) {
        if( !taskMap.containsKey(id) ){
            System.out.println("Такая задача уже удалена!");
            return;
        }
        Task task  = taskMap.get(id);
        if( task instanceof SubTask subTask){
            //Проводки по сабтаску
            Integer epicId = subTask.getEpicId();
            if( taskMap.containsKey(epicId)){
                Epic epic = (Epic) taskMap.get(epicId);
                epic.getSubTasksIds().remove(subTask.getId());
                taskMap.remove(subTask.getId());
                historyManager.remove(subTask.getId());
                updateEpicTaskStatus(epic);
            }else {
                taskMap.remove(subTask.getId());
                historyManager.remove(subTask.getId());
            }
        }else if(task instanceof Epic epic){
            System.out.println("дополнительно будут удалены все подзадачи эпика " + epic.getName());
            for(Integer removeSubTaskId: epic.getSubTasksIds()){
                taskMap.remove(removeSubTaskId);
                historyManager.remove(removeSubTaskId);
            }
            taskMap.remove(id);
            historyManager.remove(id);
        }
        else{
            taskMap.remove(id);
            historyManager.remove(id);
        }
    }

    public void updateEpicTaskStatus(Epic epic) {
        Map<Status,Integer> statusCounter = new HashMap<>();
        List<SubTask> subTasks = getAllSubTasksById(epic.getId());
        int allSubtasksCount = 0;
        for(SubTask subTask: subTasks){
            Integer statusCount = statusCounter.getOrDefault(subTask.getStatus(),0);
            statusCounter.put(subTask.getStatus(),statusCount+1);
            allSubtasksCount++;
        }

        if(allSubtasksCount == 0 || statusCounter.getOrDefault(Status.NEW,0) ==allSubtasksCount){
            epic.setStatus(Status.NEW);
        }else if(statusCounter.getOrDefault(Status.DONE,0) == allSubtasksCount){
            epic.setStatus(Status.DONE);
        }else{
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
    @Override
    public List<SubTask> getAllSubTasksById(int id) {
        if(!(taskMap.get(id) instanceof Epic)){
            System.out.println("переданный id задачи указывает не на эпик!");
            throw new IllegalArgumentException();
        }

        List<SubTask> result = new ArrayList<>();
        for( Task task: taskMap.values() ){
            if ( task instanceof SubTask subTask){
                if( subTask.getEpicId().equals(id)) result.add(subTask);
            }
        }
        return result;
    }
}

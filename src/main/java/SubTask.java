
public class SubTask extends Task{

    private Epic currentEpic;
    public SubTask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        currentEpic = epic;
    }
    @Override
    public void update(Task task){
        SubTask subTask = (SubTask) task;
        super.update(task);
        setCurrentEpic(subTask.getCurrentEpic());
    }

    public void setCurrentEpic(Epic epic){
        if( currentEpic == null){
            currentEpic = epic;
            currentEpic.addSubTask(this);
        }
        else{
            currentEpic.deleteSubTask(this);
            currentEpic = epic;
            currentEpic.addSubTask(this);
        }
    }
    public Epic getCurrentEpic(){
        return currentEpic;
    }
}

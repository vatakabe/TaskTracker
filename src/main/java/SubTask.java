public class SubTask extends Task{

    private int EpicId;
    public SubTask(String name, String description, Status status, int EpicId) {
        super(name, description, status);
        setEpicId(EpicId);
    }
    public void setEpicId( int EpicId){
        this.EpicId = EpicId;
    }
    public int getEpicId(){
        return EpicId;
    }
}

package App.History;

import App.Tasks.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task>historyList = new ArrayList<>();
    private Map<Integer, HNode> historyMap = new HashMap<>();

    private HNode<Task> head;
    private HNode<Task> tail;
    private int size = 0;
    @Override
    public void add(Task task){
        if(historyMap.containsKey(task.getId())){
            removeNode(historyMap.get(task.getId()));
        }
        historyMap.put(task.getId(), linkLast(task));
    }

    @Override
    public void remove(int id) {
        for(Task task: historyList){
            if( task.getId().equals(id)){
                historyList.remove(task);
            }
        }
    }
    public HNode linkLast(Task task){
        final HNode<Task> oldTail = tail;
        final HNode<Task> newHNode = new HNode<>(oldTail, task,  null);
        tail = newHNode;
        if(oldTail == null){
            head =newHNode;
        }else{
            oldTail.next = newHNode;
        }
        size++;
        return newHNode;
    }
    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
    public List<Task> getTasks(){
        List<Task> result = new ArrayList<>();
        HNode<Task> curNode = head;
        while(curNode != null){
            result.add(curNode.data);
            curNode = curNode.next;
        }
        return result;
    }
    public void removeNode(HNode node){
            if(node == null) return;
            if(node.prev == null){
                head = node.next;
                if(head!=null){
                    head.prev =null;
                }
            }else{
                node.prev.next =node.next;
            }
            if(node.next == null){
                tail = node.prev;
                if(tail !=null){
                    tail.next = null;
                }
            }else{
                node.next.prev = node.prev;
            }
            size--;
        }
    class HNode <T extends Task>{
        T data;
        HNode<T> prev;
        HNode<T> next;
        public HNode( HNode<T> prev,T data, HNode<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}

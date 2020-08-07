package src;

import java.util.ArrayList;
import java.util.Comparator;

public class MyPQ {
    private Comparator comp;
    private ArrayList queue;

    MyPQ(Comparator comp){
        this.comp=comp;
        this.queue=new ArrayList<>();
    }

    MyPQ(int initialCapacity,Comparator comp){
        this.comp=comp;
        this.queue=new ArrayList<>(initialCapacity);
    }

    public int size(){
        return this.queue.size();
    }

    public boolean isEmpty(){
        return this.queue.size()==0;
    }

    public MyEntry insert(Object k,Object v){
        MyEntry entry=null;
        if(v==null)
            entry=new MyEntry(k,k);
        else
            entry=new MyEntry(k,v);
        if(isEmpty())
            queue.add(entry);
        else {
            for(int i=0;i<size();i++){
                if(i==size()-1 && comp.compare(entry,queue.get(i))<=0){
                    queue.add(entry);

                    break;
                }
                if(comp.compare(entry,queue.get(i))>0) {
                    queue.add(i, entry);
                    break;
                }
            }
        }
        return entry;
    }

    public MyEntry removeMin(){
        if(size()==0)
            return null;
        MyEntry min=new MyEntry();
        if(comp.compare(queue.get(0),queue.get(size()-1))>0) {
            min=(MyEntry) queue.get(size()-1);
        }
        else {
            min = (MyEntry) queue.get(0);
        }
        queue.remove(queue.get(size() - 1));
        return min;
    }

    public MyEntry min(){
        MyEntry min=new MyEntry();
        if(comp.compare(queue.get(0),queue.get(size()-1))>0) {
            min=(MyEntry) queue.get(size()-1);
        }
        else {
            min = (MyEntry) queue.get(0);
        }
        return min;
    }
}

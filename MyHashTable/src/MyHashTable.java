package src;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashTable {
    // num of entries to the table
    private int numEntries;
    // num of bucketArray
    private int bucketCapacity;
    // load factor needed to check for rehashing
    private  float loadFactor = (float) 0.75;
    // ArrayList of bucketArray. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<FinalProject_Template.StudentInfo>> bucketArray;

    // constructor

    @Override
    public String toString() {
        return "MyHashTable{" +
                "\nnumEntries=" + numEntries +
                ", bucketCapacity=" + bucketCapacity +
                ", \nbucketArray=" + bucketArray +
                '}';
    }

    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.bucketArray = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            this.bucketArray.add(new LinkedList<>());
        }
        this.bucketCapacity = initialCapacity;
        //ADD YOUR CODE ABOVE THIS
    }

    public MyHashTable(int initialCapacity, float loadFactor) {
        // ADD YOUR CODE BELOW THIS
        this.bucketArray = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            this.bucketArray.add(new LinkedList<>());
        }
        this.bucketCapacity = initialCapacity;
        this.loadFactor=loadFactor;
        //ADD YOUR CODE ABOVE THIS
    }

    public int size() {
        return this.numEntries;
    }

    public boolean isEmpty() {
        return this.numEntries == 0;
    }

    public int bucketCapacity() {
        return this.bucketCapacity;
    }


    public ArrayList<LinkedList<FinalProject_Template.StudentInfo>> getBuckets() {
        return this.bucketArray;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    /**
     * Given a k, return the bucket position for the k.
     */
    public int hashFunction(String k) {
        int hashValue = Math.abs(k.hashCode()) % this.bucketCapacity;
        return hashValue;
    }

    /**
     * Takes a k and a v as input and adds the corresponding HashPair
     */
    public String put(String k, String v) {
        //  ADD YOUR CODE BELOW HERE

        // Make sure the k is not already in the hashtable.
        ArrayList<LinkedList<FinalProject_Template.StudentInfo>> tab = bucketArray;
        if(tab.size()==0)
            rehash(bucketCapacity);
        tab=bucketArray;
        //calculate the index of k in bucketArray
        int index = hashFunction(k);
        FinalProject_Template.StudentInfo pair = new FinalProject_Template.StudentInfo(k, v);
        LinkedList<FinalProject_Template.StudentInfo> entry = tab.get(index);
        //if entry is not null,then it indicates a hash collision has occurred
        if (entry.size() != 0) {
            for (int i = 0; i < entry.size(); i++) {
                String old =  entry.get(i).getStudentName();
                if (entry.get(i).getStudentID().equals(k)) {
                    entry.get(i).setStudentName( v);
                    return old;
                }
            }
        }

        addEntry(k, v, index);
        return null;

        //  ADD YOUR CODE ABOVE HERE
    }

    private void addEntry(String k, String v, int index) {
        
        ArrayList<LinkedList<FinalProject_Template.StudentInfo>> tab = bucketArray;
        if (numEntries + 1 >= bucketCapacity * loadFactor) {
            // Rehash the table if the threshold is exceeded
            rehash(bucketCapacity());
            tab = bucketArray;
            // 扩容之后，计算节点对应的新下标
            index = hashFunction(k);
        }
        // Creates the new entry.
        @SuppressWarnings("unchecked")
        LinkedList<FinalProject_Template.StudentInfo> e = tab.get(index);
        e.add(new FinalProject_Template.StudentInfo(k, v));
        
        numEntries++;
    }

    /**
     * Get the v corresponding to k. Expected average runtime O(1)
     */

    public String get(String k) {
        //ADD YOUR CODE BELOW HERE

        if(bucketCapacity()==0)
            rehash(1);
        int index = hashFunction(k);
        int i = 0;
        LinkedList<FinalProject_Template.StudentInfo> list = this.bucketArray.get(index);
        if (list.size() == 0)
            return null;
        while (!list.get(i).getStudentID().equals(k)) {
            if (i == list.size()-1)
                return null;
            i++;
        }
        return  this.bucketArray.get(index).get(i).getStudentName();

        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * Remove the HashPair corresponding to k . Expected average runtime O(1)
     */
    public String remove(String k) {
        //ADD YOUR CODE BELOW HERE

        ArrayList<LinkedList<FinalProject_Template.StudentInfo>> tab = bucketArray;

        //calculate the index of k in bucketArray
        int index = hashFunction(k);
        LinkedList<FinalProject_Template.StudentInfo> entry = tab.get(index);
        if (entry == null)
            return null;
        for (int i = 0; i < entry.size(); i++) {
            if (entry.get(i).getStudentID().equals(k)) {
                String v = entry.get(i).getStudentName();
                entry.remove(i);
                numEntries -= 1;
                return v;
            }
        }

        return null;
        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * Method to double the size of the hashtable if load factor increases
     */
    public void rehash(int capacity) {
        //ADD YOUR CODE BELOW HERE
        int oldCapacity = capacity;
        ArrayList<LinkedList<FinalProject_Template.StudentInfo>> oldMap = bucketArray;
        int newCapacity = oldCapacity * 2;
        if(newCapacity==0)
            newCapacity+=1;
        ArrayList<LinkedList<FinalProject_Template.StudentInfo>> newMap = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++)
            newMap.add(new LinkedList<FinalProject_Template.StudentInfo>());
        bucketArray = newMap;
        bucketCapacity = newCapacity;
        for (int i = oldCapacity; i-- > 0; ) {
            LinkedList<FinalProject_Template.StudentInfo> old = oldMap.get(i);
            for (int j = 0; j < old.size(); j++) {
                int index = hashFunction( old.get(j).getStudentID());
                newMap.get(index).add(old.get(j));
            }
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
}

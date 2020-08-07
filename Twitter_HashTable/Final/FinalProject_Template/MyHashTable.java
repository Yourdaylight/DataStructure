package FinalProject_Template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>> {
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K, V>>> buckets;

    // constructor

    @Override
    public String toString() {
        return "MyHashTable{" +
                "numEntries=" + numEntries +
                ", numBuckets=" + numBuckets +
                ", buckets=" + buckets +
                '}';
    }

    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.buckets = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            this.buckets.add(new LinkedList<>());
        }
        this.numBuckets = initialCapacity;
        //ADD YOUR CODE ABOVE THIS
    }

    public int size() {
        return this.numEntries;
    }

    public boolean isEmpty() {
        return this.numEntries == 0;
    }

    public int numBuckets() {
        return this.numBuckets;
    }

    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList<HashPair<K, V>>> getBuckets() {
        return this.buckets;
    }

    /**
     * Given a key, return the bucket position for the key.
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode()) % this.numBuckets;
        return hashValue;
    }

    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE

        // Make sure the key is not already in the hashtable.
        ArrayList<LinkedList<HashPair<K, V>>> tab = buckets;
        if(tab.size()==0)
            rehash();
        tab=buckets;
        //calculate the index of key in buckets
        int index = hashFunction(key);
        HashPair pair = new HashPair(key, value);
        LinkedList<HashPair<K, V>> entry = tab.get(index);
        //if entry is not null,then it indicates a hash collision has occurred
        if (entry.size() != 0) {
            for (int i = 0; i < entry.size(); i++) {
                V old = entry.get(i).getValue();
                if (entry.get(i).getKey().equals(key)) {
                    entry.get(i).setValue(value);
                    return old;
                }
            }
        }

        addEntry(key, value, index);
        return null;

        //  ADD YOUR CODE ABOVE HERE
    }

    private void addEntry(K key, V value, int index) {


        ArrayList<LinkedList<HashPair<K, V>>> tab = buckets;
        if (numEntries + 1 >= numBuckets * MAX_LOAD_FACTOR) {
            // Rehash the table if the threshold is exceeded
            rehash();
            tab = buckets;
            // ����֮�󣬼���ڵ��Ӧ�����±�
            index = hashFunction(key);
        }

        // Creates the new entry.
        @SuppressWarnings("unchecked")
        LinkedList<HashPair<K, V>> e = tab.get(index);
        e.add(new HashPair<K, V>(key, value));
//        tab.set(index,e);

        // �ڵ���Ŀ��1
        numEntries++;
    }


    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */

    public V get(K key) {
        //ADD YOUR CODE BELOW HERE

        if(numBuckets()==0)
            rehash();
        int index = hashFunction(key);
        int i = 0;
        LinkedList<HashPair<K, V>> list = this.buckets.get(index);
        if (list.size() == 0)
            return null;
        while (!list.get(i).getKey().equals(key)) {
            if (i == list.size()-1)
                return null;
            i++;
        }
        return (V) this.buckets.get(index).get(i).getValue();

        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1)
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE

        ArrayList<LinkedList<HashPair<K, V>>> tab = buckets;

        //calculate the index of key in buckets
        int index = hashFunction(key);
        LinkedList<HashPair<K, V>> entry = tab.get(index);
        if (entry == null)
            return null;
        for (int i = 0; i < entry.size(); i++) {
            if (entry.get(i).getKey().equals(key)) {
                V value = entry.get(i).getValue();
                entry.remove(i);
                numEntries -= 1;
                return value;
            }
        }

        return null;

        //ADD YOUR CODE ABOVE HERE
    }


    /**
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
        int oldCapacity = numBuckets();
        ArrayList<LinkedList<HashPair<K, V>>> oldMap = buckets;
        int newCapacity = oldCapacity * 2;
        if(newCapacity==0)
            newCapacity+=1;
        ArrayList<LinkedList<HashPair<K, V>>> newMap = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++)
            newMap.add(new LinkedList<HashPair<K, V>>());
        buckets = newMap;
        numBuckets = newCapacity;
        for (int i = oldCapacity; i-- > 0; ) {
            LinkedList<HashPair<K, V>> old = oldMap.get(i);
            for (int j = 0; j < old.size(); j++) {
                int index = hashFunction(old.get(j).getKey());
                newMap.get(index).add(old.get(j));
            }
        }

        //ADD YOUR CODE ABOVE HERE
    }


    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */

    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
        Iterator itr = this.iterator();
        ArrayList<K> list = new ArrayList<>();
        while (itr.hasNext()) {
            HashPair pair = (HashPair) itr.next();
            list.add((K) pair.getKey());
        }
        return list;

        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE

        ArrayList<V> list = new ArrayList<>();

        MyHashTable<V,Integer> out=new MyHashTable<>(10);
        for (HashPair<K,V> entry : this)
        {
            out.put(entry.getValue(),1);
        }
        return out.keys();

        //ADD CODE ABOVE HERE
    }


    /**
     * This method takes as input an object of type MyHashTable with values that
     * are Comparable. It returns an ArrayList containing all the keys from the map,
     * ordered in descending order based on the values they mapped to.
     * <p>
     * The time complexity for this method is O(n^2), where n is the number
     * of pairs in the map.
     */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort(MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<>();
        for (HashPair<K, V> entry : results) {
            V element = entry.getValue();
            K toAdd = entry.getKey();
            int i = sortedResults.size() - 1;
            V toCompare = null;
            while (i >= 0) {
                toCompare = results.get(sortedResults.get(i));
                if (element.compareTo(toCompare) <= 0)
                    break;
                i--;
            }
            sortedResults.add(i + 1, toAdd);
        }
        return sortedResults;
    }


    /**
     * This method takes as input an object of type MyHashTable with values that
     * are Comparable. It returns an ArrayList containing all the keys from the map,
     * ordered in descending order based on the values they mapped to.
     * <p>
     * The time complexity for this method is O(n*log(n)), where n is the number
     * of pairs in the map.
     */

    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE
//��valueֵ����
        ArrayList<K> sortedResults = new ArrayList<>();
        ArrayList<K> keys=results.keys();
        ArrayList<V> values=new ArrayList<>();
        for (K key : keys) {
            values.add(results.get(key));
        }

        try {
            sortedResults=quickSort(values,keys);
            return sortedResults;
        }catch (Exception e){
            return keys;
        }


        //ADD CODE ABOVE HERE
    }

    private static <K, V extends Comparable<V>> ArrayList<K> quickSort(ArrayList<V> arr,ArrayList<K> keys) {


        V pivot = arr.get(0); //���滻����
        K pivot1=keys.get(0);
        //int mid = arr.size()/2;
        //int pivot = arr.get(mid);
        ArrayList<V> smaller = new ArrayList<V>(); //����С�ڻ�׼ֵ������
        ArrayList<V> bigger = new ArrayList<V>(); //���ô��ڻ�׼ֵ������

        ArrayList<K> smaller1 = new ArrayList<K>(); //����С�ڻ�׼ֵ������
        ArrayList<K> bigger1 = new ArrayList<K>(); //���ô��ڻ�׼ֵ������

        //�ֽ⵱ǰ�б������
        for(int i=1; i<arr.size();i++){ //�滻i=0
//            if(i == mid)
//            continue;
            //else
            if(pivot.compareTo(arr.get(i))>=0) {
                smaller.add(arr.get(i));
                smaller1.add(keys.get(i));
            }
            else {
                bigger.add(arr.get(i));
                bigger1.add(keys.get(i));
            }
        }

        //�ݹ����
        if(smaller.size() > 1)
            quickSort(smaller,smaller1);
        if(bigger.size() > 1)
            quickSort(bigger,bigger1);

        keys.removeAll(keys);
        keys.addAll(bigger1);
        keys.add(pivot1);
        keys.addAll(smaller1);

        return keys;
    }




    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE
        private HashPair<K,V> currentNode;
        private HashPair<K,V> nextNode;
        private int currentIndex;
        //ADD YOUR CODE ABOVE HERE
    	
    	/**
    	 * Expected average runtime is O(m) where m is the number of buckets
    	 */
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
            if(MyHashTable.this.isEmpty()){
                return;
            }


            for(int i=0; i<MyHashTable.this.buckets.size(); i++){
                //:::���õ�ǰindex
                this.currentIndex = i;
                LinkedList<HashPair<K,V>> list=MyHashTable.this.buckets.get(i);
                if(list.size()!=0){
                    nextNode=list.get(0);
                    return;
                }
            }
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	
        	return (this.nextNode != null);
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE

            this.currentNode = this.nextNode;
            //:::�ݴ���Ҫ���صĽڵ�
            HashPair<K,V> needReturn = this.nextNode;

            //:::nextNodeָ���Լ���next
            LinkedList<HashPair<K,V>> list=MyHashTable.this.buckets.get(currentIndex);
            int pairIndex=list.indexOf(this.currentNode);
            if(pairIndex>=list.size()-1)
                this.nextNode=null;
            else
                this.nextNode = list.get(pairIndex+1);
            //:::�жϵ�ǰnextNode�Ƿ�Ϊnull
            if(this.nextNode == null){
                //:::˵����ǰ���ڵ�Ͱ�����Ѿ��������

                //:::Ѱ����һ���ǿյĲ��
                for(int i = this.currentIndex+1; i<MyHashTable.this.buckets.size(); i++){
                    //:::���õ�ǰindex
                    this.currentIndex = i;
                    LinkedList<HashPair<K,V>> temp=MyHashTable.this.buckets.get(i);
                    if(temp.size()==0)
                        continue;

                    HashPair<K,V> firstEntryNode =MyHashTable.this.buckets.get(i).get(0);
                    //:::�ҵ��˺�����Ϊ�յĲ��slot
                    if(firstEntryNode != null){
                        //:::nextNode = ��ǰ��۵�һ���ڵ�
                        this.nextNode = firstEntryNode;
                        //:::����ѭ��
                        break;
                    }
                }
            }

            return needReturn;
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}



interface Queue<E>{
    boolean isEmpty();
    boolean isFull();
    int size();
    void enqueue(E item);
    E dequeue();
    E front();
    void clear();
}
public class MyQueue<E> implements Queue<E> {
    private int top;
    private int stackSize;
    private MyArrayList stackArr;
    public MyQueue (int stackSize){

        this.stackSize=stackSize;
        top=0;
        stackArr=new MyArrayList(stackSize);
    }


    @Override
    public boolean isEmpty() {
        return (stackArr.isEmpty());
    }

    @Override
    public boolean isFull() {
        return (top==this.stackSize-1);
    }

    @Override
    public void enqueue (E item) {
        if(isFull()){
            System.out.println("Queue is full!");
        }else {
            stackArr.add(item);
            System.out.println("Inserted Item: "+item);
        }
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            System.out.println("Deleting fail! Queue is empty!");
            return null;
        }else {
            System.out.println("Deleted Item : "+(int)stackArr.get(top));
            E result=(E) stackArr.remove(top);
            return result;
        }
    }

    @Override
    public E front() {
        if(isEmpty()){
            System.out.println("Peeking fail! Stack is empty!");
            return null;
        }else {
            System.out.println("Peeking Item : "+(E)stackArr.get(top));
            return (E)stackArr.get(top);
        }
    }

    @Override
    public void clear() {
        if(isEmpty()){
            System.out.println("Stack is already empty!");
        }else {
            top=-1;
            stackArr=new MyArrayList(this.stackSize);
            System.out.println("Stack is clear!");
        }
    }

    public int size(){
        return top+1;
    }



    public void printStack(){
        if(isEmpty()){
            System.out.println("Stack is empty");
        }else {
            System.out.print("Stack elements : ");
            for(int i=0;i<=top;i++) {
                System.out.print(stackArr.get(i) + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        MyQueue<Integer> stack=new MyQueue<>(10);
        for(int i=1;i<=10;i++)
            stack.enqueue(i);
        while (!stack.isEmpty())
            System.out.println(stack.dequeue());
    }

}

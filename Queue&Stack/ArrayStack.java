import java.lang.reflect.Array;

interface Stack1<E>{
    boolean isEmpty();
    boolean isFull();
    void push(E item);
    E pop();
    E peek();
    void clear();
}
public class ArrayStack<E> implements Stack1<E>{
    private int top;
    private int stackSize;
    private Object stackArr[];
    public ArrayStack (int stackSize){
        top=-1;
        this.stackSize=stackSize;
        stackArr=new Object[stackSize];
    }



    @Override
    public boolean isEmpty() {
        return (top==-1);
    }

    @Override
    public boolean isFull() {
        return (top==this.stackSize-1);
    }

   @Override
    public void push(E item) {
        if(isFull()){
            System.out.println("Stack is full!");
        }else {
            stackArr[++top]=item;
            System.out.println("Inserted Item: "+item);
        }
    }

    @Override
    public E pop() {
        if(isEmpty()){
            System.out.println("Deleting fail! Stack is empty!");
            return null;
        }else {
            System.out.println("Deleted Item : "+stackArr[top]);
            return (E)stackArr[top--];
        }
    }

    @Override
    public E peek() {
        if(isEmpty()){
            System.out.println("Peeking fail! Stack is empty!");
            return null;
        }else {
            System.out.println("Peeking Item : "+stackArr[top]);
            return (E)stackArr[top];
        }
    }

    @Override
    public void clear() {
        if(isEmpty()){
            System.out.println("Stack is already empty!");
        }else {
            top=-1;
            stackArr=new Object[this.stackSize];
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
                System.out.print(stackArr[i] + " ");
            }
        }
        System.out.println();
    }

}

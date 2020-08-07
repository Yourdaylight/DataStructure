
public class ArrayQueue {
    interface Queue{
        public int size();
        public boolean isEmpty();
        public char front();
        public void enqueue(char o);
        public char dequeue();

    }

    public static class StackQueue implements Queue{
        ArrayStack<Character> inStack;
        ArrayStack<Character> outStack;

        StackQueue(int queueSize){
            inStack=new ArrayStack<Character>(queueSize);
            outStack=new ArrayStack<Character>(queueSize);
        }

        @Override
        public int size() {
            return inStack.size()+outStack.size();
        }

        @Override
        public boolean isEmpty() {
            return inStack.isEmpty();
        }

        @Override
        public char front() {
            if(!outStack.isEmpty())
                return outStack.peek();

            while(!inStack.isEmpty())
            {
                outStack.push(inStack.peek());
                inStack.pop();
            }
            return outStack.peek();
        }


        @Override
        public void enqueue(char o) {
            inStack.push(o);
        }

        @Override
        public char dequeue() {
            if (!outStack.isEmpty())
                return outStack.pop();
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
            return outStack.pop();
        }


    }

    public static void main(String[] args){
        String str1="apple";
        String str2="plication";
        String str3="y project";
        StackQueue queue=new StackQueue(100);

        //(1） apple入队列
        for(int i=0;i<str1.length();i++)
            queue.enqueue(str1.charAt(i));

        //(2) 出队3个字母
        for(int i=0;i<3;i++)
           queue.dequeue();

        //(3) plication入队列
        for(int i=0;i<str2.length();i++)
            queue.enqueue(str2.charAt(i));
        //(4) 出队5个字母
        for(int i=0;i<5;i++)
            queue.dequeue();

        //(5) y project入队列
        for(int i=0;i<str3.length();i++)
            queue.enqueue(str3.charAt(i));
        //(6) 出队4个字母
        for(int i=0;i<4;i++)
            queue.dequeue();

        //(7) 出队剩余字母并打印
        String output="";
        System.out.println(queue.size());
        int length=queue.size();
        for(int i=0;i<length;i++)
            output+=String.valueOf(queue.dequeue());
        System.out.println("===============Result below================\n"+output);
    }
}

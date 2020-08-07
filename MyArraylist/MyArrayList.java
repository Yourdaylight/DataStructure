

public class MyArrayList {
    //声明一个Object类型的数组
    private Object[] elementDate;
    //声明一个数组内元素个数
    private int size;


    //定义一个无参构造函数,初始化数组内个数
    public MyArrayList(){
        this(10);//调用另一个构造方法(有参构造方法)，所以，必须有一个有参构造方法
    }

    public MyArrayList(int initialCapacity) {//有参构造方法，实现数组容量的初始化
        if(initialCapacity<0) {
            try {
                throw new Exception();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        elementDate=new Object[initialCapacity];//初始化，容量为10    与前面的数组声明对应
    }

    //get方法，得到数组实质就是一个数组的索引操作
    public Object get(int i) {
        return elementDate[i];
    }
    public Object set(int i,Object e) {
        Object old=elementDate[i];
        elementDate[i]=e;
        return old;
    }


    public void add(Object obj) {
        //判断数组是否装满，是则扩容
        elementDate[size++]=obj;
        if(size==elementDate.length) {
            //创建一个新数组
            Object[] newArray=new Object[2*size+1];
            //将老数组拷贝到新数组内
            System.arraycopy(elementDate, 0, newArray, 0, elementDate.length);
            //再将新数组赋值给老数组
            elementDate=newArray;
        }
    }

    public Object remove(int index){
        Object obj = elementDate[index];
        if(index == size){
            elementDate[index] = null;
        }else{
            //将后边的数组向前移动一位
            System.arraycopy(elementDate, index+1, elementDate, index, size-index);
        }
        size--;
        return obj;
    }

    /***
     * 删除指定的元素，删除成功返回 true，失败返回 false
     * @param obj
     * @return
     */
    public Object remove(Object obj){
        for(int i = 0 ; i < size ; i++){
            if(obj.equals(elementDate[i])){
                return remove(i);
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void add(int index,Object obj) {
        ensureCapacity();//扩容
        System.arraycopy(elementDate,index,elementDate, index+1, size-index);
        elementDate[index]=obj;
        size++;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    private void ensureCapacity() {

        if(size==elementDate.length) {
            Object[] newarray=new Object[size*2+1];
            System.arraycopy(elementDate, 0, newarray, 0, elementDate.length);
            elementDate=newarray;
        }
    }

    public static void main(String[] args) {
        MyArrayList list=new MyArrayList();
        list.add("温暖");
        list.add("依然");
        list.add("wk");
        list.add("wk1");
        list.add("wk2");
        System.out.println("list内拥有："+list.size()+"个元素");
        list.add("666");
        list.set(5,"777");
        list.remove(4);
        System.out.println("list内拥有："+list.size()+"个元素");
        System.out.println(list.get(5));
    }
}

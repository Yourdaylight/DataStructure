

public class MyArrayList {
    //����һ��Object���͵�����
    private Object[] elementDate;
    //����һ��������Ԫ�ظ���
    private int size;


    //����һ���޲ι��캯��,��ʼ�������ڸ���
    public MyArrayList(){
        this(10);//������һ�����췽��(�вι��췽��)�����ԣ�������һ���вι��췽��
    }

    public MyArrayList(int initialCapacity) {//�вι��췽����ʵ�����������ĳ�ʼ��
        if(initialCapacity<0) {
            try {
                throw new Exception();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        elementDate=new Object[initialCapacity];//��ʼ��������Ϊ10    ��ǰ�������������Ӧ
    }

    //get�������õ�����ʵ�ʾ���һ���������������
    public Object get(int i) {
        return elementDate[i];
    }
    public Object set(int i,Object e) {
        Object old=elementDate[i];
        elementDate[i]=e;
        return old;
    }


    public void add(Object obj) {
        //�ж������Ƿ�װ������������
        elementDate[size++]=obj;
        if(size==elementDate.length) {
            //����һ��������
            Object[] newArray=new Object[2*size+1];
            //�������鿽������������
            System.arraycopy(elementDate, 0, newArray, 0, elementDate.length);
            //�ٽ������鸳ֵ��������
            elementDate=newArray;
        }
    }

    public Object remove(int index){
        Object obj = elementDate[index];
        if(index == size){
            elementDate[index] = null;
        }else{
            //����ߵ�������ǰ�ƶ�һλ
            System.arraycopy(elementDate, index+1, elementDate, index, size-index);
        }
        size--;
        return obj;
    }

    /***
     * ɾ��ָ����Ԫ�أ�ɾ���ɹ����� true��ʧ�ܷ��� false
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
        ensureCapacity();//����
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
        list.add("��ů");
        list.add("��Ȼ");
        list.add("wk");
        list.add("wk1");
        list.add("wk2");
        System.out.println("list��ӵ�У�"+list.size()+"��Ԫ��");
        list.add("666");
        list.set(5,"777");
        list.remove(4);
        System.out.println("list��ӵ�У�"+list.size()+"��Ԫ��");
        System.out.println(list.get(5));
    }
}

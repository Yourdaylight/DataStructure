package src;

public class MyBinNode extends MyNode {
    MyBinNode(){
        super();
    }
    MyBinNode(Object e){
        super(e);
    }

    public MyBinNode left(){

        return (MyBinNode) children().get(0);
    }
    public MyBinNode right(){

        return (MyBinNode) children().get(1);
    }
    public void setLeft(MyBinNode v){
        children().set(0, v);
    }

    public void setRight(MyBinNode v){
        children().set(1,v);

    }


}

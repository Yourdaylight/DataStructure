import java.util.ArrayList;
public class MyNode {
    private Object element;
    private MyNode parent;
    private ArrayList<MyNode> children;

    MyNode(){
        element=null;
        parent=null;
        children=null;
    }

    MyNode(Object e){
        element=e;
        parent=null;
        children=null;
    }

    @Override
    public String toString() {
        if(parent==null)
            return "MyNode{" +
                    "element=" + (element+"") +
                    ", parent=" +""+
                    ", children=" + children +
                    '}';
        return "MyNode{" +
                "element=" + (element+"") +
                ", parent=" +(parent.element()+"") +
                ", children=" + children +
                '}';
    }

    public Object element() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public MyNode parent() {
        return parent;
    }

    public void setParent(MyNode parent) {
        this.parent = parent;
    }

    public ArrayList<MyNode> children() {
        return children;
    }

    public void setChildren(ArrayList<MyNode> children) {
        this.children = children;
    }
    public int degree(){
        if(this.parent==null)
            return 1;
        int count=1;
        MyNode temp=this.parent;
        while (temp.parent()!=null) {
            count += 1;
            temp=temp.parent();
        }
        return count+1;
    }
}

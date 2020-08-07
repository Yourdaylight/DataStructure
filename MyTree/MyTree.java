
import java.util.ArrayList;

public class MyTree {
    private MyNode root;
    MyTree(){
        root=null;
    }
    MyTree(Object e){
        root=new MyNode(e);
        root.setChildren(new ArrayList<MyNode>());
    }

    public int size(){
        return getdeep(root(),1);
    }
    private int getdeep(MyNode root,int count){

        if(root.children()==null)
            return count;
        for (MyNode tempNode : root.children()) {
            count+=1;
            count=getdeep(tempNode,count);
        }
        return count;
    }

    public MyNode root(){
        return this.root;
    }

    public boolean isExternal(MyNode v){
        return null==v.children();
    }
    public ArrayList children(MyNode v){
        return v.children();
    }

    @Override
    public String toString() {
        return "MyTree{" +
                "root=" + root +
                '}';
    }

    public MyNode addRoot(Object e){

        MyNode newroot = new MyNode(e);
        root.setParent(newroot);
        ArrayList<MyNode> child = new ArrayList<>();
        child.add(root);
        newroot.setChildren(child);
        root = newroot;

        return root;
    }

    public MyNode addNode(Object e){
        ArrayList list=root.children();
        MyNode toadd=new MyNode();
        if(e instanceof MyNode)
            toadd=(MyNode) e;
        else
            toadd=new MyNode(e);
        toadd.setParent(root);
        list.add(toadd);
        root.setChildren(list);
        return toadd;
    }

    public MyNode addChild(MyNode v,Object e){
        MyNode toadd=searchHelp(root,v.element());
        if(e instanceof MyNode) {
            ((MyNode) e).setParent(v);
            if(toadd.children()==null) {
                ArrayList<MyNode> childs=new ArrayList();
                childs.add((MyNode) e);
                toadd.setChildren(childs);
            }
            else
                toadd.children().add((MyNode) e);
        }
        else {
            MyNode temp=new MyNode(e);
            temp.setParent(v);
            toadd.children().add(temp);
        }
        return toadd;
    }

    public MyNode addChild(MyNode v,int i,Object e){
        MyNode toadd=searchHelp(root,v.element());
        if(e instanceof MyNode) {
            ((MyNode) e).setParent(v);
            if(toadd.children()==null) {
                ArrayList<MyNode> childs=new ArrayList();
                childs.add(i,(MyNode) e);
                toadd.setChildren(childs);
            }
            else
                toadd.children().add(i,(MyNode) e);
        }
        else {
            MyNode temp=new MyNode(e);
            temp.setParent(v);
            toadd.children().add(i,temp);
        }
        return toadd;
    }

    private int searchHelp(MyNode root,MyNode result,Object searchcont) {

        if (root.element().equals(searchcont)) {
            result.setElement(root.element());
            result.setChildren(root.children());
            result.setParent(root.parent());
            return 1;
        }

        if(root.children()==null)
            return 0;
        for (MyNode tempNode : root.children()) {
            if (searchHelp(tempNode, result, searchcont) == 1)
                break;
        }

        return 0;

    }

    private MyNode searchHelp(MyNode root,Object searchcont) {

        if (root.element().equals(searchcont)) {
            MyNode res=root;
            return res;
        }

        if(root.children()==null)
            return null;
        for (MyNode tempNode : root.children()) {
            MyNode temp=searchHelp(tempNode, searchcont);
            if (temp!=null)
                return temp;
        }
        return null;
    }


    public MyNode setChild(MyNode v,int i,Object e){
        MyNode toadd=searchHelp(root,v.element());
        if(e instanceof MyNode) {
            ((MyNode) e).setParent(v);
            if(toadd.children()==null) {
                ArrayList<MyNode> childs=new ArrayList();
                childs.set(i,(MyNode) e);
                toadd.setChildren(childs);
            }
            else
                toadd.children().set(i,(MyNode) e);
        }
        else {
            MyNode temp=new MyNode(e);
            temp.setParent(v);
            toadd.children().set(i,temp);
        }
        return toadd;
    }

    public MyNode removeChild(MyNode v,int i){
        MyNode todel=searchHelp(root,v.element());
        MyNode delNode=todel.children().get(i);
        todel.children().remove(i);
        return delNode;
    }



}

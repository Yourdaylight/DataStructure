
public class MyTreeMain {
    public static void main(String[] args){
        MyTree tree=new MyTree("Interational");
        MyNode node1=new MyNode("Europen");
        MyNode node2=new MyNode("Asia");
        MyNode node3=new MyNode("Canada");
        MyNode node4=new MyNode("Sales");
        MyNode node5=new MyNode("US");
        MyNode node6=new MyNode("Computers'R Us");
        MyNode node7=new MyNode("Manufacturing");
        MyNode node8=new MyNode("Laptops");
        MyNode node9=new MyNode("Desktops");
        MyNode node10=new MyNode("R&D");

        tree.addNode(node1);
        tree.addNode(node2);
        tree.addNode(node3);
        tree.addRoot("Sales");
        tree.addChild(tree.root(),0,node5);
        tree.addRoot("Computers'R Us");
        tree.addNode(node7);
        tree.addChild(node7,node8);
        tree.addChild(node7,node9);
        tree.addNode(node10);


        for(int i=0;i<4;i++) {
            System.out.println("[Level "+i+"]");
            printlevel(tree.root(),i+1);
            System.out.println("\n");
        }
        System.out.println("* Tree size= Total "+tree.size()+" Nodes");

    }
    public static void printlevel(MyNode root,int level) {
        int degree=root.degree();
        if (degree==level) {
            System.out.print(root.element()+",");
        }
        if(root.children()==null)
            return ;
        for (MyNode tempNode : root.children()) {
            printlevel(tempNode, level);
        }
        return ;
    }

}

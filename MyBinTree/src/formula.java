package src;

public class formula {
    public static void main(String[] args) throws MyBinTree.NotExternalException {
        MyBinTree BinaryTree=new MyBinTree("+");
        MyBinNode node1=BinaryTree.addNode("*");
        MyBinNode node2=BinaryTree.addNode("*");

        MyBinNode node3=BinaryTree.inserLeft(node1,"2");
        MyBinNode node4=BinaryTree.inserRight(node1,"-");
        BinaryTree.inserLeft(node4,"3");
        BinaryTree.inserRight(node4,"1");
        BinaryTree.inserLeft(node2,"3");
        BinaryTree.inserRight(node2,"2");

        BinaryTree.inOrder(BinaryTree.root());
        System.out.println("\n="+BinaryTree.postOrder(BinaryTree.root()));

    }
}

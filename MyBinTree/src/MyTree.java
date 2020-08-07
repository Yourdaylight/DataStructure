package src;

import java.util.ArrayList;

public class MyTree {
	
	private MyNode root;
	private int totalSize;
	
	public MyTree() {
		this.root = null;
		this.totalSize = 0;		
	}
	
	public MyTree(Object e) {		
		this.root = new MyBinNode(e);					
		this.totalSize = 1;
	}
	
	public int size() {
		return this.totalSize;
	}
	
	public MyNode root() {
		return this.root;
	}
	
	public ArrayList children(MyNode v) {
		return v.children();
	}
	
	public boolean isExternal(MyNode v) {
		return v.children().isEmpty();
	}
	
	public MyNode addRoot(Object e) {
		MyNode temp = this.root;
		this.root = new MyBinNode(e);			
		this.totalSize = 1;
		return temp;		
	}
	
	public MyNode addNode(Object e) {
		MyNode newNode = new MyBinNode(e);		
		newNode.setParent(this.root);
		this.root.children().add(newNode);		
		this.totalSize++;
		return newNode;
	}
	
	public MyNode addChild(MyNode v, Object e) {
		MyNode newNode = new MyBinNode(e);	
		newNode.setParent(v);
		v.children().add(newNode);
		this.totalSize++;
		return newNode;
	}
	
	public MyNode addChild(MyNode v, int i, Object e) {
		MyNode newNode = new MyBinNode(e);		
		newNode.setParent(v);
		v.children().add(i, newNode);
		this.totalSize++;
		return newNode;
	}
	
	public MyNode setChild(MyNode v, int i, Object e) {
		MyNode newNode = new MyBinNode(e);		
		newNode.setParent(v);
		v.children().set(i, newNode);
		return newNode;
	}
	
	public MyNode removeChild(MyNode v, int i) {
		this.totalSize--;
		return (MyNode)v.children().remove(i);
	}

}

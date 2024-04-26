// Microsoft interview question
// largestNodePath means count nodes and not the edges

package com.ms;

class Node {
	int data;
	Node left;
	Node right;
	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

public class Main {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.right = new Node(2);
		root.right.left = new Node(3);
		root.right.right = new Node(2);
		root.right.left.right = new Node(5);
		root.right.right.left = new Node(5);
		System.out.println(largestNodePathPassingThroughRootNode(root));
	}

	public static int largestNodePathPassingThroughRootNode(Node node) {
		return height(node.left) + height(node.right) + 1;
	}
	
	public static int height(Node root) {
		if(root == null) return 0;
		return Math.max(height(root.left), height(root.right)) + 1;
	}
	
}

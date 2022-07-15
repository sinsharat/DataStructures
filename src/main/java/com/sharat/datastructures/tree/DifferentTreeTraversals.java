package com.sharat.datastructures.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DifferentTreeTraversals {

	// breadth first traversals
	
	/**
	 * breadthFirstTraversal
	 * 
	 * @param node Node
	 */
	public void breadthFirstTraversal(Node node) {
		if (node == null) {
			return;
		}

		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(node);

		Node queueNode;
		Node leftNode;
		Node rightNode;
		while ((queueNode = queue.poll()) != null) {
			System.out.print(queueNode.getVal() + " ");
			leftNode = queueNode.getLeft();
			rightNode = queueNode.getRight();
			if (leftNode != null) {
				queue.add(leftNode);
			}
			if (rightNode != null) {
				queue.add(rightNode);
			}
		}
	}

	// depth first traversals types

	/**
	 * preOrderTraversal
	 * 
	 * @param Node node
	 */
	public void preOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.getVal() + " ");
		preOrderTraversal(node.getLeft());
		preOrderTraversal(node.getRight());
	}

	/**
	 * inOrderTraversal
	 * 
	 * @param node Node
	 */
	public void inOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		inOrderTraversal(node.getLeft());
		System.out.print(node.getVal() + " ");
		inOrderTraversal(node.getRight());
	}

	/**
	 * postOrderTraversal
	 * 
	 * @param node Node
	 */
	public void postOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		postOrderTraversal(node.getLeft());
		postOrderTraversal(node.getRight());
		System.out.print(node.getVal() + " ");
	}

	public static void main(String[] args) {
		//	  	  1
		//		/   \
		//	   2     3
		//	  / \   / \
		//	 4   5 6   7
		Node rootNode = new Node(1);
		Node leftNode = new Node(2);
		Node rightNode = new Node(3);
		Node leftleftNode = new Node(4);
		Node leftrightNode = new Node(5);
		Node rightleftNode = new Node(6);
		Node rightrightNode = new Node(7);
		rootNode.setLeft(leftNode);
		rootNode.setRight(rightNode);
		leftNode.setLeft(leftleftNode);
		leftNode.setRight(leftrightNode);
		rightNode.setLeft(rightleftNode);
		rightNode.setRight(rightrightNode);

		DifferentTreeTraversals differentTreeTraversals = new DifferentTreeTraversals();
		System.out.print("Breadth First Traversal: ");
		differentTreeTraversals.breadthFirstTraversal(rootNode);
		System.out.print("\nDepth First Pre-Order Traversal: ");
		differentTreeTraversals.preOrderTraversal(rootNode);
		System.out.print("\nDepth First In-Order Traversal: ");
		differentTreeTraversals.inOrderTraversal(rootNode);
		System.out.print("\nDepth First Post-Order Traversal: ");
		differentTreeTraversals.postOrderTraversal(rootNode);
	}
}

class Node {

	private int val;

	private Node left;

	private Node right;

	public Node(int val) {
		super();
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [val=");
		builder.append(val);
		builder.append(", left=");
		builder.append(left != null ? left.val : null);
		builder.append(", right=");
		builder.append(right != null ? right.val : null);
		builder.append("]");
		return builder.toString();
	}
}

package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSize {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}
	}

	public int findBinaryTreeSizeUsingDepthTraversal(Node node) {

		if (node == null) {
			return 0;
		}

		return findBinaryTreeSizeUsingDepthTraversal(node.left) + findBinaryTreeSizeUsingDepthTraversal(node.right) + 1;
	}

	public int findBinaryTreeSizeUsingBreadthTraversal(Node node) {
		
		if (node == null) {
			return 0;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		
		int size = 0;
		Node queueNode;
		Node leftNode;
		Node rightNode;
		while((queueNode = queue.poll()) != null) {
			size++;
			leftNode = queueNode.left;
			rightNode = queueNode.right;
			
			if (leftNode != null) {
				queue.add(leftNode);
			}
			
			if (rightNode != null) {
				queue.add(rightNode);
			}
		}
		
		return size;
	}

	public static void main(String[] args) {
		/*
		 * 			  20 
		 * 		    /    \ 
		 *         8     12 
		 *        / \   / 
		 *       3  5  8 
		 *            / 
		 *           1
		 */
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(12);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right.left = new Node(8);
		root.right.left.left = new Node(1);

		BinaryTreeSize binaryTreeSize = new BinaryTreeSize();
		System.out
				.println("TreeSizeUsingDepthTraversal: " + binaryTreeSize.findBinaryTreeSizeUsingDepthTraversal(root));
		System.out.println(
				"TreeSizeUsingBreadthTraversal: " + binaryTreeSize.findBinaryTreeSizeUsingBreadthTraversal(root));
	}

}

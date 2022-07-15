package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxValueInBinaryTree {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}
	}

	public int findBinaryTreeMaxValueByDepthFirstMethod(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int nodeMaxValue = Math.max(findBinaryTreeMaxValueByDepthFirstMethod(node.left),
				findBinaryTreeMaxValueByDepthFirstMethod(node.right));

		return Math.max(nodeMaxValue, node.data);
	}

	public int findBinaryTreeMaxValueByBreadthFirstMethod(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);

		Node queueNode;
		Node leftNode;
		Node rightNode;
		int maxValue = Integer.MIN_VALUE;

		while ((queueNode = queue.poll()) != null) {
			maxValue = Math.max(maxValue, queueNode.data);

			leftNode = queueNode.left;
			rightNode = queueNode.right;

			if (leftNode != null) {
				queue.offer(leftNode);
			}

			if (rightNode != null) {
				queue.offer(rightNode);
			}

		}

		return maxValue;
	}

	public static void main(String[] args) {
		/*
		 * 			  15 
		 * 		    /    \ 
		 *         8     12 
		 *        / \   / 
		 *       3  5  20 
		 *            / 
		 *           1
		 */
		Node root = new Node(15);
		root.left = new Node(8);
		root.right = new Node(12);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right.left = new Node(20);
		root.right.left.left = new Node(1);

		MaxValueInBinaryTree maxValueInBinaryTree = new MaxValueInBinaryTree();
		System.out.println("BinaryTreeMaxValueByDepthFirstMethod: "
				+ maxValueInBinaryTree.findBinaryTreeMaxValueByDepthFirstMethod(root));
		System.out.println("BinaryTreeMaxValueByBreadthFirstMethod: "
				+ maxValueInBinaryTree.findBinaryTreeMaxValueByBreadthFirstMethod(root));
	}

}

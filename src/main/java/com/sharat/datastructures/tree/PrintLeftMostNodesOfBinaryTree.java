package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintLeftMostNodesOfBinaryTree {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}
	}

	private int maxLevel = 0;
	
	private void printLeftMostNodeByDepthFirstMethod(Node node) {
		maxLevel = 0;
		printLeft(node, 1);
	}
	
	private void printLeft(Node node, int level) {
		if (node == null) {
			return;
		}
		
		if (maxLevel < level) {
			maxLevel = level;
			System.out.print(node.data + " ");
		}
		
		printLeft(node.left, level + 1);
		printLeft(node.right, level + 1);
	}

	private void printLeftMostNodeByBreadthFirstMethod(Node node) {
		if (node == null) {
			return;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);

		Node queueNode;
		Node leftNode;
		Node rightNode;

		int queueSize = queue.size();
		while (queueSize > 0) {
			for (int i = 0; i < queueSize; i++) {
				queueNode = queue.poll();
				// the first node will always be left most node of that level since we are
				// putting left node first always.
				if (i == 0) {
					System.out.print(queueNode.data + " ");
				}

				leftNode = queueNode.left;
				if (leftNode != null) {
					queue.offer(leftNode);
				}

				rightNode = queueNode.right;
				if (rightNode != null) {
					queue.offer(rightNode);
				}
			}

			queueSize = queue.size();
		}

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

		PrintLeftMostNodesOfBinaryTree printLeftMostNodesOfBinaryTree = new PrintLeftMostNodesOfBinaryTree();
		System.out.print("BinaryTreeMaxValueByDepthFirstMethod: ");
		printLeftMostNodesOfBinaryTree.printLeftMostNodeByDepthFirstMethod(root);
		System.out.print("\nBinaryTreeMaxValueByBreadthFirstMethod: ");
		printLeftMostNodesOfBinaryTree.printLeftMostNodeByBreadthFirstMethod(root);
	}

}

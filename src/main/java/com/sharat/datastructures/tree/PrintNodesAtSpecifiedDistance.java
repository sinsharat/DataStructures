package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintNodesAtSpecifiedDistance {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}
	}

	public void printKthNodeUsingDepthFirst(Node node, int k) {
		if (node == null) {
			return;
		}

		if (k == 0) {
			System.out.print(node.data + " ");
			return;
		}

		printKthNodeUsingDepthFirst(node.left, k - 1);
		printKthNodeUsingDepthFirst(node.right, k - 1);
	}

	public void printKthNodeUsingBreadthFirst(Node node, int k) {
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		
		Node queueNode;
		Node leftNode;
		Node rightNode;
		
		int queueSize = queue.size();
		while (queueSize > 0) {
			for (int i = 0; i < queueSize; i++) {
				queueNode = queue.poll();
				
				if (k == 0) {
					System.out.print(queueNode.data + " ");
					continue;
				}
				
				leftNode = queueNode.left;
				if (leftNode != null) {
					queue.add(leftNode);
				}
				
				rightNode = queueNode.right;
				if (rightNode != null) {
					queue.add(rightNode);
				}
			}
			
			queueSize = queue.size();
			k--;
		}

	}

	public static void main(String[] args) {
		/*
		 * 				 20 
		 * 			   /    \ 
		 *            8     12 
		 *           / \   /  \ 
		 *          3   5 8    9 
		 *               / 
		 *              1
		 */
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(12);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right.left = new Node(8);
		root.right.right = new Node(9);
		root.right.left.left = new Node(1);

		PrintNodesAtSpecifiedDistance printNodesAtSpecifiedDistance = new PrintNodesAtSpecifiedDistance();
		System.out.print("printKthNodeUsingDepthFirst: ");
		printNodesAtSpecifiedDistance.printKthNodeUsingDepthFirst(root, 2);
		System.out.print("\nprintKthNodeUsingBreadthFirst: ");
		printNodesAtSpecifiedDistance.printKthNodeUsingBreadthFirst(root, 2);
	}

}

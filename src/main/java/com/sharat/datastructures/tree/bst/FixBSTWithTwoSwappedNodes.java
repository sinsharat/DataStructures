package com.sharat.datastructures.tree.bst;

import java.util.ArrayList;
import java.util.List;

public class FixBSTWithTwoSwappedNodes {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [data=");
			builder.append(data);
			builder.append(", left=");
			builder.append(left != null ? left.data : "");
			builder.append(", right=");
			builder.append(right != null ? right.data : "");
			builder.append("]");
			return builder.toString();
		}
	}

	private Node previous, first, second = null;

	public void fixBSTWithTwoSwappedNodes(Node root) {
		if (root == null) {
			return;
		}

		fixNodes(root);

		if (first == null) {
			return;
		}

		int temp = second.data;
		second.data = first.data;
		first.data = temp;

		return;
	}

	private void fixNodes(Node node) {
		if (node == null) {
			return;
		}

		fixNodes(node.left);

		if (previous != null && previous.data > node.data) {

			if (first == null) {
				first = previous;
			}

			second = node;
		}

		previous = node;
		fixNodes(node.right);
	}

	public List<Integer> getInOrderList(Node node) {
		List<Integer> inOrderList = new ArrayList<>();
		if (node == null) {
			return inOrderList;
		}

		generateInOrderList(node, inOrderList);

		return inOrderList;
	}

	private void generateInOrderList(Node node, List<Integer> inOrderList) {
		if (node == null) {
			return;
		}
		generateInOrderList(node.left, inOrderList);
		inOrderList.add(node.data);
		generateInOrderList(node.right, inOrderList);
	}

	public static void main(String[] args) {
		/*                 11
		 *               /    \
         *              /      \
         *             /        \
         *            /          \
         *           21           18
         *          /   \      /     \ 
         *         3    10    13      30
         *       /  \        /  \    /  \
         *      1    4      12  15  9   33
         */
		Node root = new Node(11);
		root.left = new Node(21);
		root.right = new Node(18);
		root.left.left = new Node(3);
		root.left.right = new Node(10);
		root.left.left.left = new Node(1);
		root.left.left.right = new Node(4);
		root.right.left = new Node(13);
		root.right.right = new Node(30);
		root.right.left.left = new Node(12);
		root.right.left.right = new Node(15);
		root.right.right.left = new Node(9);
		root.right.right.right = new Node(33);

		FixBSTWithTwoSwappedNodes fixBSTWithTwoSwappedNodes = new FixBSTWithTwoSwappedNodes();
		System.out.print("Inorder faulty node: " + fixBSTWithTwoSwappedNodes.getInOrderList(root));
		fixBSTWithTwoSwappedNodes.fixBSTWithTwoSwappedNodes(root);
		System.out.print("\nInorder fixed node: " + fixBSTWithTwoSwappedNodes.getInOrderList(root));
	}

}

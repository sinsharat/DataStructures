package com.sharat.datastructures.tree.bst;

public class CheckIfTreeIsBST {

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

	public boolean checkForBinarySearchTree(Node root) {
		if (root == null) {
			return true;
		}
		return checkNodeMeetsBinarySearchTreeNeeds(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean checkNodeMeetsBinarySearchTreeNeeds(Node node, int lowerRange, int highRange) {
		if (node == null) {
			return true;
		}
		
		if (node.data < lowerRange || node.data > highRange) {
			return false;
		}

		if (checkNodeMeetsBinarySearchTreeNeeds(node.left, lowerRange, node.data)
				&& checkNodeMeetsBinarySearchTreeNeeds(node.right, node.data, highRange)) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		/*                 11
		 *               /    \
         *              /      \
         *             /        \
         *            /          \
         *            9           18
         *          /   \      /     \ 
         *         3    10    13      30
         *       /  \        /  \    /  \
         *      1    4      12  15  21  33
         */
		Node root = new Node(11);
		root.left = new Node(9);
		root.right = new Node(18);
		root.left.left = new Node(3);
		root.left.right = new Node(10);
		root.left.left.left = new Node(1);
		root.left.left.right = new Node(4);
		root.right.left = new Node(13);
		root.right.right = new Node(30);
		root.right.left.left = new Node(12);
		root.right.left.right = new Node(15);
		root.right.right.left = new Node(21);
		root.right.right.right = new Node(33);
		
		CheckIfTreeIsBST checkIfTreeIsBST = new CheckIfTreeIsBST();
		System.out.println("Is binary search tree: " + checkIfTreeIsBST.checkForBinarySearchTree(root));
		
		root.right.left.left = new Node(10);
		System.out.println("Is binary search tree: " + checkIfTreeIsBST.checkForBinarySearchTree(root));
	}

}

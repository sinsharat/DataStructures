package com.sharat.datastructures.tree.bst;

import java.util.HashMap;
import java.util.Map;

public class VerticalSumInBST {

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

	public void findVerticalSum(Node root) {
		if (root == null) {
			return;
		}
		Map<Integer, Integer> verticalNumberSumpMap = new HashMap<>();
		prepareVerticalSumData(root, 0, verticalNumberSumpMap);
		for (Map.Entry<Integer, Integer> levelSum : verticalNumberSumpMap.entrySet()) {
			System.out.println("Level " + levelSum.getKey() + " : " + levelSum.getValue());
		}
	}

	private void prepareVerticalSumData(Node node, Integer column, Map<Integer, Integer> verticalNumberSumpMap) {
		if (node == null) {
			return;
		}
		int value;
		if (verticalNumberSumpMap.get(column) != null) {
			value = verticalNumberSumpMap.get(column) + node.data;
		} else {
			value = node.data;
		}
		verticalNumberSumpMap.put(column, value);
		prepareVerticalSumData(node.left, column - 1, verticalNumberSumpMap);
		prepareVerticalSumData(node.right, column + 1, verticalNumberSumpMap);

	}

	public static void main(String[] args) {
		/*                11(0)
		 *               /    \
         *              /      \
         *             /        \
         *            /          \
         *            9(-1)     18(1)
         *          /   \      /     \ 
         *        3(-2) 10(0) 13(0)   30(2)
         *       /  \        /  \      /  \
         *    1(-3) 4(-1) 12(-1) 15(1)21(1)33(3)
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

		VerticalSumInBST verticalSumInBST = new VerticalSumInBST();
		verticalSumInBST.findVerticalSum(root);
	}

}

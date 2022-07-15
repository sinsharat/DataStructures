package com.sharat.datastructures.tree.bst;

import java.util.Map;
import java.util.TreeMap;

public class TopAndBottomViewInBST {

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

	public void topView(Node root) {
		if (root == null) {
			return;
		}
		Map<Integer, Integer> verticalNumberSumpMap = new TreeMap<>();
		prepareTopView(root, 0, verticalNumberSumpMap);
		for (Map.Entry<Integer, Integer> levelSum : verticalNumberSumpMap.entrySet()) {
			System.out.println("Level " + levelSum.getKey() + " : " + levelSum.getValue());
		}
	}
	
	private void prepareTopView(Node node, Integer column, Map<Integer, Integer> verticalNumberSumpMap) {
		if (node == null) {
			return;
		}
		if (verticalNumberSumpMap.get(column) == null) {
			verticalNumberSumpMap.put(column, node.data);
		}
		
		prepareTopView(node.left, column - 1, verticalNumberSumpMap);
		prepareTopView(node.right, column + 1, verticalNumberSumpMap);

	}
	
	public void bottomView(Node root) {
		if (root == null) {
			return;
		}
		Map<Integer, Integer> verticalNumberSumpMap = new TreeMap<>();
		prepareBottomView(root, 0, verticalNumberSumpMap);
		for (Map.Entry<Integer, Integer> levelSum : verticalNumberSumpMap.entrySet()) {
			System.out.println("Level " + levelSum.getKey() + " : " + levelSum.getValue());
		}
	}
	
	private void prepareBottomView(Node node, Integer column, Map<Integer, Integer> verticalNumberSumpMap) {
		if (node == null) {
			return;
		}
		verticalNumberSumpMap.put(column, node.data);
		
		prepareBottomView(node.left, column - 1, verticalNumberSumpMap);
		prepareBottomView(node.right, column + 1, verticalNumberSumpMap);

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

		TopAndBottomViewInBST topAndBottomViewInBST = new TopAndBottomViewInBST();
		System.out.println("Top View: ");
		topAndBottomViewInBST.topView(root);
		
		System.out.println("Bottom View: ");
		topAndBottomViewInBST.bottomView(root);
	}

}

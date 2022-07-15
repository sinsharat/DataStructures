package com.sharat.datastructures.tree.bst;

import java.util.HashSet;
import java.util.Set;

public class PairWithAGivenSumInBST {

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

	public static class Result {
		private boolean data;

		public Result(boolean data) {
			this.data = data;
		}
	}

	public void findPairWithGivenSum(Node root, int sum) {
		if (root == null) {
			System.out.println("No pair exists.");
			return;
		}
		Set<Integer> nodeData = new HashSet<Integer>();
		Result result = new Result(false);
		findPair(root, nodeData, sum, result);

		if (!result.data) {
			System.out.println("No pair exists.");
		}
	}

	private void findPair(Node node, Set<Integer> nodeData, int sum, Result result) {
		if (node == null) {
			return;
		}

		if (result.data) {
			return;
		}

		if (nodeData.contains(sum - node.data)) {
			System.out.println("foundPair : " + node.data + ", " + (sum - node.data));
			result.data = true;
			return;
		}

		nodeData.add(node.data);

		findPair(node.left, nodeData, sum, result);
		findPair(node.right, nodeData, sum, result);

	}

	public static void main(String[] args) {
		/*
		 * 11 / \ / \ / \ / \ 9 18 / \ / \ 3 10 13 30 / \ / \ / \ 1 4 12 15 21 33
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

		PairWithAGivenSumInBST pairWithAGivenSumInBST = new PairWithAGivenSumInBST();
		System.out.println("Checking sum pair for number 39");
		pairWithAGivenSumInBST.findPairWithGivenSum(root, 39);
		System.out.println("Checking sum pair for number 62");
		pairWithAGivenSumInBST.findPairWithGivenSum(root, 62);
	}
}

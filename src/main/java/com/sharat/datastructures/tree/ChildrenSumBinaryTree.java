package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

// check whether a binary is a children sum binary tree.
public class ChildrenSumBinaryTree {

	public static class Node {
		int data;

		Node left, right = null;

		public Node(int data) {
			this.data = data;
		}
	}

	public boolean checkChildrenBinarySumTreeDepthFirstMethod(Node node) {

		if (node == null) {
			return true;
		}

		Node left = node.left;
		Node right = node.right;

		if (left == null && right == null) {
			return true;
		}

		int parentData = node.data;

		int leftData = left != null ? left.data : 0;
		int rightData = right != null ? right.data : 0;

		if (parentData == (leftData + rightData)) {
			return checkChildrenBinarySumTreeDepthFirstMethod(left)
					&& checkChildrenBinarySumTreeDepthFirstMethod(right);
		}

		return false;

	}

	public boolean checkChildrenBinarySumTreeBreadthFirstMethod(Node node) {
		if (node == null) {
			return true;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);

		Node parentNode;
		Node leftNode;
		Node rightNode;

		int parentValue;
		int leftNodeValue;
		int rightNodeValue;

		int size = queue.size();
		while (size > 0) {
			for (int i = 0; i < size; i++) {
				parentNode = queue.poll();
				leftNode = parentNode.left;
				rightNode = parentNode.right;
				if (leftNode == null && rightNode == null) {
					continue;
				}

				parentValue = parentNode.data;
				
				if (leftNode != null) {
					leftNodeValue = leftNode.data;
					queue.add(leftNode);
				} else {
					leftNodeValue = 0;
				}

				if (rightNode != null) {
					rightNodeValue = rightNode.data;
					queue.add(rightNode);
				} else {
					rightNodeValue = 0;
				}

				if (parentValue != (leftNodeValue + rightNodeValue)) {
					return false;
				}
			}

			size = queue.size();
		}

		return true;
	}

	public static void main(String[] args) {
		Node root = new Node(20);
		Node leftNode = new Node(9);
		Node leftLeftNode = new Node(4);
		Node leftRightNode = new Node(5);
		Node rightNode = new Node(11);
		root.left = leftNode;
		root.right = rightNode;
		leftNode.left = leftLeftNode;
		leftNode.right = leftRightNode;

		ChildrenSumBinaryTree childrenSumBinaryTree = new ChildrenSumBinaryTree();
		System.out.println(
				"checkChildrenBinarySumTreeDepthFirstMethod: " + childrenSumBinaryTree.checkChildrenBinarySumTreeDepthFirstMethod(root));
		System.out.println(
				"checkChildrenBinarySumTreeBreadthFirstMethod: " + childrenSumBinaryTree.checkChildrenBinarySumTreeBreadthFirstMethod(root));
	}

}

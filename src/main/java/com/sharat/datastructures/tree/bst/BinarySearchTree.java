package com.sharat.datastructures.tree.bst;

public class BinarySearchTree {

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

	public boolean search(Node node, int num) {

		if (node == null) {
			return false;
		}

		while (node != null) {
			if (node.data == num) {
				return true;
			} else if (node.data > num) {
				node = node.left;
			} else {
				node = node.right;
			}
		}

		return false;
	}

	public void add(Node node, int num) {
		Node childNode = new Node(num);
		if (node == null) {
			return;
		}

		Node parentNode = null;
		while (node != null) {
			if (node.data == num) {
				return;
			} else if (node.data > num) {
				parentNode = node;
				node = node.left;
			} else {
				parentNode = node;
				node = node.right;
			}
		}

		if (parentNode.data < num) {
			parentNode.right = childNode;
		} else {
			parentNode.left = childNode;
		}
	}

	public Node delete(Node node, int num) {
		Node parentNode = null;
		while (node != null) {
			if (node.data == num) {
				break;
			} else if (node.data > num) {
				parentNode = node;
				node = node.left;
			} else {
				parentNode = node;
				node = node.right;
			}
		}
		
		if (node == null) {
			return null;
		}
		
		Node successorNode = findSuccessorNode(node);
		successorNode.left = node.left;
		successorNode.right = node.right;
		if (parentNode.left == node) {
			parentNode.left = successorNode;
		} else {
			parentNode.right = successorNode;
		}
		return node;
	}
	
	private Node findSuccessorNode(Node node) {
		if (node.left == null && node.right == null) { // node to be deleted is leaf node.
			return null;
		} else if (node.right == null) { // node has only left child
			return node.left;
		} else {
			Node parentNode = null;
			node = node.right;
			while (node.left != null) {
				parentNode = node;
				node = node.left;
			}
			parentNode.left = null;
			return node;
		}
	}

	public Node floor(Node node, int num) {
		if (node == null) {
			return null;
		}

		Node floorNode = null;
		while (node != null) {
			if (node.data == num) {
				return node;
			} else if (node.data > num) {
				node = node.left;
			} else {
				floorNode = node;
				node = node.right;
			}
		}

		return floorNode;
	}
	
	public Node ceil(Node node, int num) {
		if (node == null) {
			return null;
		}

		Node ceilNode = null;
		while (node != null) {
			if (node.data == num) {
				return node;
			} else if (node.data > num) {
				ceilNode = node;
				node = node.left;
			} else {
				node = node.right;
			}
		}

		return ceilNode;
	}
	
	public void printPreOrder(Node node) {
		if (node == null) {
			return;
		}
		
		System.out.print(node.data + " ");
		printPreOrder(node.left);
		printPreOrder(node.right);
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

		BinarySearchTree binarySearchTree = new BinarySearchTree();
		System.out.println("BST pre-order structure before modification: ");
		binarySearchTree.printPreOrder(root);
		System.out.println("");
		System.out.println("Going to add value 19 to bst.");
		binarySearchTree.add(root, 19);
		System.out.println("BST pre-order structure after adding 19: ");
		binarySearchTree.printPreOrder(root);
		System.out.println("");
		System.out.println("Search result for 19 after adding it: " + binarySearchTree.search(root, 19));
		System.out.println("Going to delete value 18 from bst.");
		binarySearchTree.delete(root, 18);
		System.out.println("Search result for 18 after deleting it: " + binarySearchTree.search(root, 18));
		System.out.println("BST pre-order structure after modification: ");
		binarySearchTree.printPreOrder(root);
		System.out.println("");
		System.out.println("Floor value of 22: " + binarySearchTree.floor(root, 22).data);
		System.out.println("Ceil value of 24: " + binarySearchTree.ceil(root, 24).data);
	}

}

package com.sharat.datastructures.tree;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Stack;

public class BinaryTree<T> {

	private final Comparator<? super T> comparator;

	private Node<T> root = null;

	private int size;

	public BinaryTree() {
		comparator = null;
	}

	public BinaryTree(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	static final class Node<T> {
		T value;
		Node<T> leftNode;
		Node<T> rightNode;

		public T setValue(T value) {
			T oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		public T getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node<?> other = (Node<?>) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", leftNode=" + leftNode.value + ", rightNode=" + rightNode.value + "]";
		}
	}

	public boolean insert(T t) {
		if (t == null) {
			return false;
		}
		Node<T> node = new Node<>();
		node.setValue(t);
		if (null == root) {
			root = node;
			size++;
			return true;
		}
		Node<T> curNode = root;
		Node<T> compareNode;
		while (true) {
			Comparator<? super T> cpr = comparator;
			int val;
			if (null != cpr) {
				val = cpr.compare(t, curNode.value);
			} else {
				@SuppressWarnings("unchecked")
				Comparable<? super T> cpbl = (Comparable<? super T>) t;
				val = cpbl.compareTo(curNode.value);
			}
			if (val == 0) {
				return false;
			} else if (val < 0) {
				compareNode = curNode.leftNode;
				if (compareNode == null || compareNode.value == null) {
					curNode.leftNode = node;
					size++;
					return true;
				}
			} else {
				compareNode = curNode.rightNode;
				if (compareNode == null || compareNode.value == null) {
					curNode.rightNode = node;
					size++;
					return true;
				}
			}
			curNode = compareNode;
		}
	}

	public int getSize() {
		return size;
	}

	public Node<T> find(T t) {
		if (null == t) {
			return null;
		}

		Node<T> curNode = root;
		int val;
		Comparator<? super T> cpr = comparator;
		while (curNode != null) {
			if (cpr != null) {
				val = cpr.compare(t, curNode.value);
			} else {
				@SuppressWarnings("unchecked")
				Comparable<? super T> comprbl = (Comparable<? super T>) t;
				val = comprbl.compareTo(curNode.value);
			}

			if (val == 0) {
				return curNode;
			} else if (val < 0) {
				curNode = curNode.leftNode;
			} else {
				curNode = curNode.rightNode;
			}
		}
		return null;
	}

	public void delete(T t) {
		if (null == t) {
			return;
		}

		Node<T> delNode = root;
		Node<T> parentNode = null;
		int nodeIndex = 0;
		int val;
		Comparator<? super T> cpr = comparator;
		while (delNode != null) {

			if (cpr != null) {
				val = cpr.compare(t, delNode.value);
			} else {
				@SuppressWarnings("unchecked")
				Comparable<? super T> comprbl = (Comparable<? super T>) t;
				val = comprbl.compareTo(delNode.value);
			}

			if (val == 0) {
				break;
			}

			parentNode = delNode;

			if (val < 0) {
				delNode = delNode.leftNode;
				nodeIndex = -1;
			} else {
				delNode = delNode.rightNode;
				nodeIndex = 1;
			}
		}

		if (delNode == null) {
			return;
		}

		// no child of deleted node
		if (delNode.leftNode == null && delNode.rightNode == null) { // both child nodes left node and right node are
			if (nodeIndex == 0) {
				root = null;
			} else if (nodeIndex < 0) {
				parentNode.leftNode = null;
			} else {
				parentNode.rightNode = null;
			}
			size--;
		} else if (delNode.rightNode == null) { // only right node is null, left node is present
			if (nodeIndex == 0) {
				root = delNode.leftNode;
			} else if (nodeIndex < 0) {
				parentNode.leftNode = delNode.leftNode;
			} else {
				parentNode.rightNode = delNode.leftNode;
			}
			size--;
		} else if (delNode.leftNode == null) { // only left node is null, right node is present
			if (nodeIndex == 0) {
				root = delNode.rightNode;
			} else if (nodeIndex < 0) {
				parentNode.leftNode = delNode.rightNode;
			} else {
				parentNode.rightNode = delNode.rightNode;
			}
			size--;
		} else { // both child nodes i.e. left node and right node are not null
			// get successor of node to delete (current)
			Node<T> successor = getSuccessor(delNode);
			if (nodeIndex == 0) {
				root = successor;
			} else if (nodeIndex < 0) {
				parentNode.leftNode = successor;
			} else {
				parentNode.rightNode = successor;
			}
			// connect successor to current’s left child
			successor.leftNode = delNode.leftNode;
			size--;
		}
	}

	private Node<T> getSuccessor(Node<T> delNode) {
		Node<T> successorParent = delNode;
		Node<T> successor = delNode;
		Node<T> current = delNode.rightNode; // go to right child
		while (current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftNode; // go to left child
		}
		// if successor not
		if (successor != delNode.rightNode) // right child,
		{ // make connections
			successorParent.leftNode = successor.rightNode;
			successor.rightNode = delNode.rightNode;
		}
		return successor;
	}

	public void traverse(int traverseType) {
		switch (traverseType) {
		case 0:
			System.out.println("Pre-order traversal:");
			preorder(root);
			break;
		case 1:
			System.out.println("In-order traversal:");
			inorder(root);
			break;
		case 2:
			System.out.println("Post-order traversal:");
			postorder(root);
			break;
		default:
			break;
		}
	}

	private void postorder(Node<T> curNode) {
		if (curNode != null) {
			postorder(curNode.leftNode);
			postorder(curNode.rightNode);
			System.out.println(curNode.value);
		}
	}

	private void inorder(Node<T> curNode) {
		if (curNode != null) {
			inorder(curNode.leftNode);
			System.out.println(curNode.value);
			inorder(curNode.rightNode);
		}
	}

	private void preorder(Node<T> curNode) {
		if (curNode != null) {
			System.out.println(curNode.value);
			preorder(curNode.leftNode);
			preorder(curNode.rightNode);
		}
	}

	public void displayTree() {
		Stack<Node<T>> globalStack = new Stack<Node<T>>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack<Node<T>> localStack = new Stack<Node<T>>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(" ");
			while (!globalStack.isEmpty()) {
				Node<T> temp = (Node<T>) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.value);
					localStack.push(temp.leftNode);
					localStack.push(temp.rightNode);
					if (temp.leftNode != null || temp.rightNode != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(" ");
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	}
	
	public static void main(String []args) throws NoSuchAlgorithmException {
		BinaryTree<Integer> btree = new BinaryTree<Integer>();
		int nos = SecureRandom.getInstanceStrong().nextInt(20);
		System.out.println("Got total nos of insert as : " + nos + 1);
		for (int i = 0; i < nos/2; i++) {
			btree.insert(SecureRandom.getInstanceStrong().nextInt(1000));
		}
		btree.insert(79);
		for (int i = nos/2; i < nos; i++) {
			btree.insert(SecureRandom.getInstanceStrong().nextInt(1000));
		}
		System.out.println("Tree Size before deletion: " + btree.getSize());
		btree.traverse(0);
		btree.traverse(1);
		btree.traverse(2);
		btree.displayTree();
		System.out.println(btree.find(79));
		btree.delete(79);
		btree.displayTree();
		System.out.println("Tree Size after deletion: " + btree.getSize());
	}
}

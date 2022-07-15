package com.sharat.datastructures.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Tree234Adv<T> {

	private static final int ORDER = 4;

	private final Comparator<T> comparator;

	private Node<T> root = null;

	public Tree234Adv() {
		this.comparator = null;
	}

	public Tree234Adv(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	private static final class Node<T> {
		private int numItems = 0;
		private Node<T> parent;
		@SuppressWarnings("unchecked")
		private Node<T>[] children = new Node[ORDER];
		private Object[] nodeData = new Object[ORDER - 1];

		public void connectChild(int index, Node<T> child) {
			children[index] = child;
			if (child != null) {
				child.parent = this;
			}
		}

		public Node<T> disconnectChild(int index) {
			Node<T> child = children[index];
			children[index] = null;
			return child;
		}

		public Node<T> getChild(int index) {
			return children[index];
		}

		public Node<T> getParent() {
			return parent;
		}

		public int getNumItems() {
			return numItems;
		}

		@SuppressWarnings("unchecked")
		public T getItem(int index) {
			return (T) nodeData[index];
		}

		public boolean isLeaf() {
			return children[0] == null ? true : false;
		}

		public boolean isFull() {
			return numItems == ORDER - 1 ? true : false;
		}

		private T removeItem() {
			@SuppressWarnings("unchecked")
			T item = (T) nodeData[numItems];
			nodeData[numItems] = null;
			numItems--;
			return item;
		}

		public boolean findItem(T item) {
			for (int i = 0; i < numItems; i++) {
				if (item.equals(nodeData[i])) {
					return true;
				}
			}
			return false;
		}

		public void addItem(int index, T item) {
			numItems++;
			nodeData[index] = item;
		}

		public void displayNode() {
			System.out.println("[");
			for (int i = 0; i < nodeData.length; i++) {
				System.out.println("{");
				System.out.println(nodeData[i]);
				System.out.println("}");
			}
			System.out.println("]");
		}
	}

	private int addNodeItem(Node<T> node, T item) {
		for (int j = ORDER - 2; j > 0; j++) {
			T compareData = node.getItem(j);
			if (compareData == null) {
				continue;
			} else {
				if (comparator != null) {
					if (comparator.compare(item, compareData) > 0) {
						node.nodeData[j + 1] = node.nodeData[j];
					} else {
						node.addItem(j + 1, item);
						return j + 1;
					}
				} else {
					@SuppressWarnings("unchecked")
					Comparable<? super T> cpbl = (Comparable<? super T>) item;
					if (cpbl.compareTo(compareData) > 0) {
						node.nodeData[j + 1] = node.nodeData[j];
					} else {
						node.addItem(j + 1, item);
						return j + 1;
					}
				}
			}
		}
		node.addItem(0, item);
		return 0;
	}

	private Node<T> getNextChild(Node<T> theNode, T value) {
		int j;
		// assumes node is not empty, not full, not a leaf
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) {
			if (comparator != null) {
				if (comparator.compare(value, theNode.getItem(j)) < 0) {
					return theNode.getChild(j); // return left child
				}
			} else {
				@SuppressWarnings("unchecked")
				Comparable<? super T> comparable = (Comparable<? super T>) value;
				if (comparable.compareTo(theNode.getItem(j)) < 0) {
					return theNode.getChild(j); // return left child
				}
			}
		}
		return theNode.getChild(j); // return right child
	}

	private void split(Node<T> thisNode) {
		Node<T> child3 = thisNode.disconnectChild(2);
		Node<T> child4 = thisNode.disconnectChild(3);
		T itemC = thisNode.removeItem();
		T itemB = thisNode.removeItem();
		Node<T> rightNode = new Node<T>();
		int index = 0;
		Node<T> parentNode;
		if (thisNode == root) {
			root = new Node<T>();
			;
			parentNode = root;
			parentNode.addItem(0, itemB);
			thisNode.parent = parentNode;
			parentNode.connectChild(0, thisNode);
		} else {
			parentNode = thisNode.parent;
			index = addNodeItem(parentNode, itemB);
		}
		parentNode.connectChild(index + 1, rightNode);
		rightNode.addItem(0, itemC);
		rightNode.connectChild(0, child3);
		rightNode.connectChild(1, child4);
		rightNode.parent = thisNode.parent;
	}

	public void insert(T value) {
		Node<T> curNode = root;
		while (true) {
			if (curNode.isFull()) {
				split(curNode);
				curNode = curNode.getParent();
				curNode = getNextChild(curNode, value);
			} else if (curNode.isLeaf()) {
				addNodeItem(curNode, value);
				break;
			} else {
				curNode = getNextChild(curNode, value);
			}
		}
	}

	public boolean find(T value) {
		Node<T> curNode = root;
		while (true) {
			if (curNode.findItem(value)) {
				return true; // found it
			} else if (curNode.isLeaf()) {
				return false; // can't find it
			} else { // search deeper
				curNode = getNextChild(curNode, value);
			}
		}
	}

	public void remove(T value) {
		Node<T> curNode = root;
		while (true) {
			if (curNode.findItem(value)) {
				Node<T> valNode = performRearrangeOfTree(curNode, value);
				int numItems = valNode.numItems;
				for (int i = 0; i < numItems; i++) {
					if (value.equals(valNode.getItem(i))) {
						valNode.nodeData[i] = null;
						valNode.numItems--;
						return;
					}
				}
			} else if (curNode.isLeaf()) {
				return; // can't find it
			} else {// search deeper
				curNode = getNextChild(curNode, value);
			}
		}
	}
	
	private Node<T> performRearrangeOfTree(Node<T> thisNode, T value) {
		if (thisNode.isLeaf() && thisNode.getNumItems() > 1) {
			return thisNode;
		}
		
		return null;
	}

	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node<T> thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode(); // display this node

		// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node<T> nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	}

}

////////////////////////////////////////////////////////////////
class Tree234AdvApp {
	public static void main(String[] args) throws IOException {
		double value;
		Tree234Adv<Number> theTree = new Tree234Adv<Number>();

		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);

		while (true) {
			putText("Enter first letter of ");
			putText("show, insert, or find: ");
			char choice = getChar();
			switch (choice) {
			case 's':
				theTree.displayTree();
				break;
			case 'i':
				putText("Enter value to insert: ");
				value = getInt();
				theTree.insert(value);
				break;
			case 'f':
				putText("Enter value to find: ");
				value = getInt();
				if (theTree.find(value))
					System.out.println("Found " + value);
				else
					System.out.println("Could not find " + value);
				break;
			default:
				putText("Invalid entry\n");
			} // end switch
		} // end while
	} // end main()
		// --------------------------------------------------------------

	public static void putText(String s) {
		System.out.print(s);
		System.out.flush();
	}

	// --------------------------------------------------------------
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	// --------------------------------------------------------------
	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	// -------------------------------------------------------------
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
	// -------------------------------------------------------------
} // end class Tree234App

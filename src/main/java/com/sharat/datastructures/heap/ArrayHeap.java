package com.sharat.datastructures.heap;

import java.io.*;

public class ArrayHeap {
	private Node[] heapArray;
	private int maxSize; // size of array
	private int currentSize; // number of nodes in array

	private class Node {
		private int iData; // data item (key)

		public Node(int key) // constructor
		{
			iData = key;
		}

		public int getKey() {
			return iData;
		}

		public void setKey(int id) {
			iData = id;
		}

	}

	public ArrayHeap(int mx) // constructor
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize]; // create array
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean insert(int key) {
		if (currentSize == maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	} // end insert()

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];
		while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
			heapArray[index] = heapArray[parent]; // move it down
			index = parent;
			parent = (parent - 1) / 2;
		} // end while
		heapArray[index] = bottom;
	} // end trickleUp()

	public Node remove() // delete item with max key
	{ // (assumes non-empty list)
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	} // end remove()

	public void trickleDown(int index) {
		int largerChild;
		Node top = heapArray[index]; // save root
		while (index < currentSize / 2) // while node has at
		{ // least one child,
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find larger child
			if (rightChild < currentSize && // (rightChild exists?)
					heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
				largerChild = rightChild;
			else
				largerChild = leftChild;
			// top >= largerChild?
			if (top.getKey() >= heapArray[largerChild].getKey())
				break;
			// shift child up
			heapArray[index] = heapArray[largerChild];
			index = largerChild; // go down
		} // end while
		heapArray[index] = top; // root to index
	} // end trickleDown()

	public boolean change(int index, int newValue) {
		if (index < 0 || index >= currentSize)
			return false;
		int oldValue = heapArray[index].getKey(); // remember old
		heapArray[index].setKey(newValue); // change to new
		if (oldValue < newValue) // if raised,
			trickleUp(index); // trickle it up
		else // if lowered,
			trickleDown(index); // trickle it down
		return true;
	} // end change()

	public void displayArrayHeap() {
		System.out.print("heapArray: "); // array format
		for (int m = 0; m < currentSize; m++)
			if (heapArray[m] != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots + dots); // dotted top line
		while (currentSize > 0) // for each heap item
		{
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++) // preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(heapArray[j].getKey());
			if (++j == currentSize) // done?
				break;
			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else // next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' '); // interim blanks
		} // end for
		System.out.println("\n" + dots + dots); // dotted bottom line
	} // end displayArrayHeap()

	public static void main(String[] args) throws IOException {
		int value, value2;
		ArrayHeap theArrayHeap = new ArrayHeap(31); // make a ArrayHeap; max size 31
		boolean success;
		theArrayHeap.insert(70); // insert 10 items
		theArrayHeap.insert(40);
		theArrayHeap.insert(50);
		theArrayHeap.insert(20);
		theArrayHeap.insert(60);
		theArrayHeap.insert(100);
		theArrayHeap.insert(80);
		theArrayHeap.insert(30);
		theArrayHeap.insert(10);
		theArrayHeap.insert(90);
		while (true) // until [Ctrl]-[C]
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, remove, change: ");
			int choice = getChar();
			switch (choice) {
			case 's': // show
				theArrayHeap.displayArrayHeap();
				break;
			case 'i': // insert
				System.out.print("Enter value to insert: ");
				value = getInt();
				success = theArrayHeap.insert(value);
				if (!success)
					System.out.println("Can't insert; heap full");
				break;
			case 'r': // remove
				if (!theArrayHeap.isEmpty())
					theArrayHeap.remove();
				else
					System.out.println("Can't remove; heap empty");
				break;
			case 'c': // change
				System.out.print("Enter current index of item: ");
				value = getInt();
				System.out.print("Enter new key: ");
				value2 = getInt();
				success = theArrayHeap.change(value, value2);
				if (!success)
					System.out.println("Invalid index");
				break;
			default:
				System.out.println("Invalid entry\n");
			} // end switch
		} // end while
	} // end main()

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

} // end class ArrayHeap

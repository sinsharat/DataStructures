package com.sharat.datastructures.tree;

//tree234.java
//demonstrates 234 tree
//to run this program: C>java Tree234App
import java.io.*; // for I/O
import java.lang.Integer; // for parseInt()
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

public class Tree234 {
	private TreeNode root = new TreeNode(); // make root node
	// -------------------------------------------------------------

	public int find(double key) {
		TreeNode curNode = root;
		int childNumber;
		while (true) {
			if ((childNumber = curNode.findItem(key)) != -1)
				return childNumber; // found it
			else if (curNode.isLeaf())
				return -1; // can't find it
			else // search deeper
				curNode = getNextChild(curNode, key);
		} // end while
	}

	// -------------------------------------------------------------
	// insert a DataItem
	public void insert(double dValue)

	// Performs the splits
	// in a top-down (root -----> leaf) fashion.

	{
		TreeNode curNode = root;
		DataItem tempItem = new DataItem(dValue);

		while (true) {
			if (curNode.isFull()) // if node full,
			{
				split(curNode); // split it
				curNode = curNode.getParent(); // back up
				// search once
				curNode = getNextChild(curNode, dValue);
			} // end if(node is full)

			else if (curNode.isLeaf()) // if node is leaf,
				break; // go insert
			// node is not full, not a leaf; so go to lower level
			else
				curNode = getNextChild(curNode, dValue);
		} // end while

		curNode.insertItem(tempItem); // insert new DataItem
	} // end insert()
		// -------------------------------------------------------------

	public void split(TreeNode thisNode) // split the node
	{
		// assumes node is full
		DataItem itemB, itemC;
		TreeNode parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem(); // remove items from
		itemB = thisNode.removeItem(); // this node
		child2 = thisNode.disconnectChild(2); // remove children
		child3 = thisNode.disconnectChild(3); // from this node

		TreeNode newRight = new TreeNode(); // make new node

		if (thisNode == root) // if this is the root,
		{
			root = new TreeNode(); // make new root
			parent = root; // root is our parent
			root.connectChild(0, thisNode); // connect to parent
		} else {// this node not the root
			parent = thisNode.getParent(); // get parent
		}
		// deal with parent
		itemIndex = parent.insertItem(itemB); // item B to parent
		int n = parent.getNumItems(); // total items?

		for (int j = n - 1; j > itemIndex; j--) // move parent's
		{ // connections
			TreeNode temp = parent.disconnectChild(j); // one child
			parent.connectChild(j + 1, temp); // to the right
		}
		// connect newRight to parent
		parent.connectChild(itemIndex + 1, newRight);

		// deal with newRight
		newRight.insertItem(itemC); // item C to newRight
		newRight.connectChild(0, child2); // connect to 0 and 1
		newRight.connectChild(1, child3); // on newRight
	} // end split()
		// -------------------------------------------------------------
		// gets appropriate child of node during search for value

	public TreeNode getNextChild(TreeNode theNode, double theValue) {

		// Should be able to do this w/o a loop, since we should know
		// index of correct child already

		int j;
		// assumes node is not empty, not full, not a leaf
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) // for each item in node
		{ // are we less?
			if (theValue < theNode.getItem(j).dData)
				return theNode.getChild(j); // return left child
		} // end for // we're greater, so
		return theNode.getChild(j); // return right child
	}

	// -------------------------------------------------------------
	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	// -------------------------------------------------------------
	private void recDisplayTree(TreeNode thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode(); // display this node

		// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			TreeNode nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	} // end recDisplayTree()
		// -------------------------------------------------------------\
} // end class Tree234

class DataItem {
	public double dData; // one data item
	// --------------------------------------------------------------

	public DataItem(double dd) // constructor
	{
		dData = dd;
	}

	// --------------------------------------------------------------
	public void displayItem() // display item, format "/27"
	{
		System.out.print("/" + dData);
	}
	// --------------------------------------------------------------
} // end class DataItem
	////////////////////////////////////////////////////////////////

class TreeNode {
	private static final int ORDER = 4;
	private int numItems;
	private TreeNode parent;
	private TreeNode childArray[] = new TreeNode[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER - 1];

	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	// -------------------------------------------------------------
	// connect child to this node
	public void connectChild(int childNum, TreeNode child) {
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}

	// -------------------------------------------------------------
	// disconnect child from this node, return it
	public TreeNode disconnectChild(int childNum) {
		TreeNode tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	// -------------------------------------------------------------
	public TreeNode getChild(int childNum) {
		return childArray[childNum];
	}

	// -------------------------------------------------------------
	public TreeNode getParent() {
		return parent;
	}

	// -------------------------------------------------------------
	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	// -------------------------------------------------------------
	public int getNumItems() {
		return numItems;
	}

	// -------------------------------------------------------------
	public DataItem getItem(int index) // get DataItem at index
	{
		return itemArray[index];
	}

	// -------------------------------------------------------------
	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	// -------------------------------------------------------------
	public int findItem(double key) // return index of

	// Should use binary search if ORDER is large.
	// Could also return the index of the pointer to chase
	// if the item is not found (for example: return -i means
	// key not found, but you need to follow childArray[i]
	// this would save looping in getnextchild

	{ // item (within node)
		for (int j = 0; j < ORDER - 1; j++) // if found,
		{ // otherwise,
			if (itemArray[j] == null) // return -1
				break;
			else if (itemArray[j].dData == key)
				return j;
		}
		return -1;
	} // end findItem
	// -------------------------------------------------------------

	public int insertItem(DataItem newItem) {
		// assumes node is not full
		numItems++; // will add new item
		double newKey = newItem.dData; // key of new item

		// Should start this for loop at numItems-1, rather than ORDER -2

		for (int j = ORDER - 2; j >= 0; j--) // start on right,
		{ // examine items
			if (itemArray[j] == null) // if item null,
				continue; // go left one cell
			else // not null,
			{ // get its key
				double itsKey = itemArray[j].dData;
				if (newKey < itsKey) // if it's bigger
					itemArray[j + 1] = itemArray[j]; // shift it right
				else {
					itemArray[j + 1] = newItem; // insert new item
					return j + 1; // return index to
				} // new item
			} // end else (not null)
		} // end for // shifted all items,
		itemArray[0] = newItem; // insert new item
		return 0;
	} // end insertItem()
	// -------------------------------------------------------------

	public DataItem removeItem() // remove largest item
	{
		// assumes node not empty
		DataItem temp = itemArray[numItems - 1]; // save item
		itemArray[numItems - 1] = null; // disconnect it
		numItems--; // one less item
		return temp; // return item
	}

	// -------------------------------------------------------------
	public void displayNode() // format "/24/56/74/"
	{
		for (int j = 0; j < numItems; j++)
			itemArray[j].displayItem(); // "/56"
		System.out.println("/"); // final "/"
	}
	// -------------------------------------------------------------
} // end class TreeNode

////////////////////////////////////////////////////////////////
class Tree234App {
	public static void main(String[] args) throws IOException {
		double value;
		Tree234 theTree = new Tree234();

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
				int found = theTree.find(value);
				if (found != -1)
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

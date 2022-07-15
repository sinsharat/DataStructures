package com.sharat.datastructures.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map.Entry;

/**
 * Huffman encoding obeys the huffman algorithm. It compresses the input
 * sentence and serializes the "huffman code" and the "tree" used to generate
 * the huffman code Both the serialized files are intended to be sent to client.
 */
public final class Huffman {
	
	private static final String CUR_PATH = Paths.get("").toAbsolutePath().toString();
	private static final String TREE_FILE = CUR_PATH + "/tree";
	private static final String CHAR_FILE = CUR_PATH + "/chars";
	private static final String ENCODE_FILE = CUR_PATH + "/encode";

	private static Huffman huffman = new Huffman();
	
	public static Huffman getInstance() {
		return huffman;
	}
	
	private Huffman() {
		try {
			File treePath = new File(TREE_FILE);
			if (!treePath.exists()) {
				treePath.createNewFile();
			}

			File charPath = new File(CHAR_FILE);
			if (!charPath.exists()) {
				charPath.createNewFile();
			}

			File encodePath = new File(ENCODE_FILE);
			if (!encodePath.exists()) {
				encodePath.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class HuffmanNode {
		char ch;
		int frequency;
		HuffmanNode left;
		HuffmanNode right;

		HuffmanNode(char ch, int frequency, HuffmanNode left, HuffmanNode right) {
			this.ch = ch;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "HuffmanNode [ch=" + ch + ", frequency=" + frequency + ", left=" + left + ", right=" + right + "]";
		}
	}

	private class HuffManComparator implements Comparator<HuffmanNode> {
		@Override
		public int compare(HuffmanNode node1, HuffmanNode node2) {
			return node1.frequency - node2.frequency;
		}
	}

	/**
	 * Compresses the string using huffman algorithm. The huffman tree and the
	 * huffman code are serialized to disk
	 * 
	 * @param sentence
	 *            The sentence to be serialized
	 * @throws FileNotFoundException
	 *             If file is not found
	 * @throws IOException
	 *             If IO exception occurs.
	 */
	public void compress(String sentence) throws FileNotFoundException, IOException {
		System.out.println("Start Hoffman encoding");
		if (sentence == null) {
			throw new NullPointerException("Input sentence cannot be null.");
		}
		if (sentence.length() == 0) {
			throw new IllegalArgumentException("The string should atleast have 1 character.");
		}
		System.out.println("Sentence to encode is : " + sentence);
		final Map<Character, Integer> charFreq = getCharFrequency(sentence);
		System.out.println("Got Char frequqncy map as : " + charFreq);
		final HuffmanNode root = buildTree(charFreq);
		System.out.println("Got Huffman tree as : " + root);
		final Map<Character, String> charCode = generateCodes(root);
		System.out.println("Got encoded char code map as : " + charCode);
		final String encodedMessage = encodeMessage(charCode, sentence);
		System.out.println("Got encocded message as : " + encodedMessage);
		serializeTree(root);
		serializeMessage(encodedMessage);
		System.out.println("End Hoffman encoding.");
	}

	private Map<Character, Integer> getCharFrequency(String sentence) {
		final Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			if (!map.containsKey(ch)) {
				map.put(ch, 1);
			} else {
				int val = map.get(ch);
				map.put(ch, ++val);
			}
		}
		return map;
	}

	/**
	 * Map<Character, Integer> map Some implementation of that treeSet is passed as
	 * parameter.
	 * 
	 * @param map
	 */
	private HuffmanNode buildTree(Map<Character, Integer> map) {
		final Queue<HuffmanNode> nodeQueue = createNodeQueue(map);

		while (nodeQueue.size() > 1) {
			final HuffmanNode node1 = nodeQueue.remove();
			final HuffmanNode node2 = nodeQueue.remove();
			HuffmanNode node = new HuffmanNode('\0', node1.frequency + node2.frequency, node1, node2);
			nodeQueue.add(node);
		}

		// remove it to prevent object leak.
		return nodeQueue.remove();
	}

	private Queue<HuffmanNode> createNodeQueue(Map<Character, Integer> map) {
		final Queue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(11, new HuffManComparator());
		for (Entry<Character, Integer> entry : map.entrySet()) {
			pq.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
		}
		return pq;
	}

	private Map<Character, String> generateCodes(HuffmanNode node) {
		final Map<Character, String> map = new HashMap<Character, String>();
		doGenerateCode(node, map, "");
		return map;
	}

	private void doGenerateCode(HuffmanNode node, Map<Character, String> map, String s) {
		if (node.left == null && node.right == null) {
			map.put(node.ch, s);
			return;
		}
		doGenerateCode(node.left, map, s + '0');
		doGenerateCode(node.right, map, s + '1');
	}

	private String encodeMessage(Map<Character, String> charCode, String sentence) {
		final StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < sentence.length(); i++) {
			stringBuilder.append(charCode.get(sentence.charAt(i)));
		}
		return stringBuilder.toString();
	}

	private void serializeTree(HuffmanNode node) throws FileNotFoundException, IOException {
		final BitSet bitSet = new BitSet();
		try (ObjectOutputStream oosTree = new ObjectOutputStream(new FileOutputStream(TREE_FILE))) {
			try (ObjectOutputStream oosChar = new ObjectOutputStream(new FileOutputStream(CHAR_FILE))) {
				IntObject o = new IntObject();
				preOrder(node, oosChar, bitSet, o);
				bitSet.set(o.bitPosition, true); // padded to mark end of bit set relevant for deserialization.
				oosTree.writeObject(bitSet);
			}
		}
	}

	private class IntObject {
		int bitPosition;
	}

	/*
	 * Algo: 1. Access the node 2. Register the value in bit set.
	 * 
	 * 
	 * here true and false dont correspond to left branch and right branch. there, -
	 * true means "a branch originates from leaf" - false mens
	 * "a branch originates from non-left".
	 * 
	 * Also since branches originate from some node, the root node must be provided
	 * as source or starting point of initial branches.
	 * 
	 * Diagram and how an bit set would look as a result. (source node) / \ true
	 * true / \ (leaf node) (leaf node) | | false false | |
	 * 
	 * So now a bit set looks like [false, true, false, true]
	 * 
	 */
	private void preOrder(HuffmanNode node, ObjectOutputStream oosChar, BitSet bitSet, IntObject intObject)
			throws IOException {
		if (node.left == null && node.right == null) {
			bitSet.set(intObject.bitPosition++, false); // register branch in bitset
			oosChar.writeChar(node.ch);
			return; // DONT take the branch.
		}
		bitSet.set(intObject.bitPosition++, true); // register branch in bitset
		preOrder(node.left, oosChar, bitSet, intObject); // take the branch.

		bitSet.set(intObject.bitPosition++, true); // register branch in bitset
		preOrder(node.right, oosChar, bitSet, intObject); // take the branch.
	}

	private void serializeMessage(String message) throws IOException {
		final BitSet bitSet = getBitSet(message);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(ENCODE_FILE))) {

			oos.writeObject(bitSet);
		}
	}

	private BitSet getBitSet(String message) {
		final BitSet bitSet = new BitSet();
		int i = 0;
		for (i = 0; i < message.length(); i++) {
			if (message.charAt(i) == '0') {
				bitSet.set(i, false);
			} else {
				bitSet.set(i, true);
			}
		}
		bitSet.set(i, true); // dummy bit set to know the length
		return bitSet;
	}

	/**
	 * Retrieves back the original string.
	 * 
	 * 
	 * @return The original uncompressed string
	 * @throws FileNotFoundException
	 *             If the file is not found
	 * @throws ClassNotFoundException
	 *             If class is not found
	 * @throws IOException
	 *             If IOException occurs
	 */
	public String expand() throws FileNotFoundException, ClassNotFoundException, IOException {
		final HuffmanNode root = deserializeTree();
		return decodeMessage(root);
	}

	private HuffmanNode deserializeTree() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream oisBranch = new ObjectInputStream(new FileInputStream(TREE_FILE))) {
			try (ObjectInputStream oisChar = new ObjectInputStream(new FileInputStream(CHAR_FILE))) {
				final BitSet bitSet = (BitSet) oisBranch.readObject();
				return preOrder(bitSet, oisChar, new IntObject());
			}
		}
	}

	/*
	 * Construct a tree from: input [false, true, false, true, (dummy true to mark
	 * the end of bit set)] The input is constructed from preorder traversal
	 * 
	 * Algo: 1 Create the node. 2. Read what is registered in bitset, and decide if
	 * created node is supposed to be a leaf or non-leaf
	 * 
	 */
	private HuffmanNode preOrder(BitSet bitSet, ObjectInputStream oisChar, IntObject o) throws IOException {
		// created the node before reading whats registered.
		final HuffmanNode node = new HuffmanNode('\0', 0, null, null);

		// reading whats registered and determining if created node is the leaf or
		// non-leaf.
		if (!bitSet.get(o.bitPosition)) {
			o.bitPosition++; // feed the next position to the next stack frame by doing computation before
								// preOrder is called.
			node.ch = oisChar.readChar();
			return node;
		}

		o.bitPosition = o.bitPosition + 1; // feed the next position to the next stack frame by doing computation before
											// preOrder is called.
		node.left = preOrder(bitSet, oisChar, o);

		o.bitPosition = o.bitPosition + 1; // feed the next position to the next stack frame by doing computation before
											// preOrder is called.
		node.right = preOrder(bitSet, oisChar, o);

		return node;
	}

	private String decodeMessage(HuffmanNode node)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(ENCODE_FILE))) {
			final BitSet bitSet = (BitSet) ois.readObject();
			System.out.println("decodeMessage Got bitSet as : " + bitSet);
			final StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < (bitSet.length() - 1);) {
				HuffmanNode temp = node;
				// since huffman code generates full binary tree, temp.right is certainly null
				// if temp.left is null.
				while (temp.left != null) {
					if (!bitSet.get(i)) {
						temp = temp.left;
					} else {
						temp = temp.right;
					}
					i = i + 1;
				}
				stringBuilder.append(temp.ch);
			}
			return stringBuilder.toString();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Huffman huffman = Huffman.getInstance();
		// even number of characters
		huffman.compress("some");
		System.out.println("some".equals(huffman.expand()));

//		// odd number of characters
//		huffman.compress("someday");
//		System.out.println("someday".equals(huffman.expand()));
//
//		// repeating even number of characters + space + non-ascii
//		huffman.compress("some some#");
//		System.out.println("some some#".equals(huffman.expand()));
//
//		// odd number of characters + space + non-ascii
//		huffman.compress("someday someday&");
//		System.out.println("someday someday&".equals(huffman.expand()));
	}
}

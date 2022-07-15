package com.sharat.datastructures.trie;

public class TrieInsertSearchDelete {

	class TrieNode {
		private static final int DEFAULT_CAPACITY = 256;

		TrieNode[] chidren;
		boolean isEnd;

		public TrieNode() {
			chidren = new TrieNode[DEFAULT_CAPACITY];
		}

		public TrieNode(int capacity) {
			chidren = new TrieNode[capacity];
		}
	}

	private TrieNode root = new TrieNode();

	public void insert(String data) {
		if (data == null) {
			return;
		}

		TrieNode node = root;

		char[] charData = data.toCharArray();
		TrieNode childNode;
		for (int charater : charData) {
			childNode = node.chidren[charater];
			if (childNode == null) {
				childNode = new TrieNode();
				node.chidren[charater] = childNode;
			}
			node = childNode;
		}
		node.isEnd = true;

	}

	public boolean search(String data) {
		if (data == null) {
			return false;
		}

		TrieNode node = root;

		char[] charData = data.toCharArray();
		TrieNode childNode;
		for (int charater : charData) {
			childNode = node.chidren[charater];
			if (childNode == null) {
				return false;
			}
			node = childNode;
		}
		return node.isEnd;

	}

	public void delete(String data) {
		performDeletion(root, 0, data);
	}

	public TrieNode performDeletion(TrieNode node, int index, String data) {
		if (isEmpty(node)) {
			return null;
		}

		if (index == data.length()) {
			node.isEnd = false;
			if (isEmpty(node)) {
				return null;
			}
			return node;
		}

		int character = data.charAt(index);

		node.chidren[character] = performDeletion(node.chidren[character], index+1, data);

		if (isEmpty(node)) {
			return null;
		}

		return node;
	}

	public boolean isEmpty(TrieNode node) {
		if (node == null) {
			return true;
		}

		TrieNode[] children = node.chidren;
		if (children == null) {
			return true;
		}
		int childrenNodeLength = children.length;
		for (int i = 0; i < childrenNodeLength; i++) {
			if (children[i] != null) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		TrieInsertSearchDelete trie = new TrieInsertSearchDelete();
		System.out.println("Inserting: bad");
		trie.insert("bad");
		System.out.println("Inserting: bat");
		trie.insert("bat");
		System.out.println("Inserting: geeks");
		trie.insert("geeks");
		System.out.println("Inserting: geek");
		trie.insert("geek");
		System.out.println("Inserting: cat");
		trie.insert("cat");
		System.out.println("Inserting: cut");
		trie.insert("cut");
		System.out.println("Inserting: zoo");
		trie.insert("zoo");
		System.out.println("Inserting: A quik brown fox jumped over the lazy dog.");
		trie.insert("A quik brown fox jumped over the lazy dog.");

		System.out.println("Search for geek returned: " + trie.search("geek"));
		System.out.println("Search for zoo returned: " + trie.search("zoo"));
		System.out.println("Search for bat returned: " + trie.search("bat"));
		System.out.println("Search for cut returned: " + trie.search("cut"));
		System.out.println("Search for gee returned: " + trie.search("gee"));
		System.out.println("Search for eek returned: " + trie.search("eek"));
		System.out.println("Search for A quik brown fox jumped over the lazy dog. returned: "
				+ trie.search("A quik brown fox jumped over the lazy dog."));
		System.out.println("Search for A quik brown fox returned: " + trie.search("A quik brown fox"));

		System.out.println("Going to delete geek");
		trie.delete("geek");
		System.out.println("Search for geek returned: " + trie.search("geek"));
		System.out.println("Search for geeks returned: " + trie.search("geeks"));
		System.out.println("Going to delete cat");
		trie.delete("cat");
		System.out.println("Search for cat returned: " + trie.search("cat"));
		System.out.println("Search for cut returned: " + trie.search("cut"));
		System.out.println("Going to delete zoo");
		trie.delete("zoo");
		System.out.println("Search for zoo returned: " + trie.search("zoo"));
	}
}

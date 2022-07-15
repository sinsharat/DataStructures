package com.sharat.datastructures.trie;

public class CountDistinctRowsInABinaryMatrix {

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

	private TrieNode root = new TrieNode(2);
	
	public int countDistinctRows(int[][] binaryMatrix) {
		
		if (binaryMatrix == null) {
			return 0;
		}
		
		TrieNode node;
		TrieNode childNode;
		int count = 0;
		for (int[] row : binaryMatrix) {
			if (row == null) {
				continue;
			}
			node = root;
			for (int rowVal : row) {
				childNode = node.chidren[rowVal];
				if (childNode == null) {
					childNode = new TrieNode(2);
					node.chidren[rowVal] = childNode;
				}
				
				node = childNode;
			}
			
			if (!node.isEnd) {
				node.isEnd = true;
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		CountDistinctRowsInABinaryMatrix countDistinctRowsInABinaryMatrix = new CountDistinctRowsInABinaryMatrix();

		int[][] binaryMatrix1 = { { 1, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 }, { 1, 1, 1 } };
		System.out.println("Distinct Rows in binary Matrix { { 1, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 }, { 1, 1, 1 } }: "
				+ countDistinctRowsInABinaryMatrix.countDistinctRows(binaryMatrix1));

		int[][] binaryMatrix2 = { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 } };
		System.out.println(
				"Distinct Rows in binary Matrix { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 } }: "
						+ countDistinctRowsInABinaryMatrix.countDistinctRows(binaryMatrix2));
	}

}

package com.sharat.datastructures.greedy;

import java.util.PriorityQueue;

public class HauffmanCompressionEncoding {

	public void generateHuffmanCodes(char[] characters, int[] frequencies) {
		
		if (characters == null || frequencies == null || characters.length != frequencies.length) {
			return;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < characters.length; i++) {
			pq.add(new Node(characters[i], frequencies[i]));
		}
		
		Node node = null;
		Node firstNode;
		Node secondNode;
		while (pq.size() > 1) {
			firstNode = pq.poll();
			secondNode = pq.poll();
			node = new Node('$', firstNode.frequency + secondNode.frequency);
			node.left = firstNode;
			node.right = secondNode;
			pq.add(node);
		}
		
		printCodes(node, "");
	}
	
	private void printCodes(Node node, String code) {
		if (node == null) {
			return;
		}
		
		if (node.character != '$') {
			System.out.println(node.character + ":" + code);
		}
		
		printCodes(node.left, code + "0");
		printCodes(node.right, code + "1");
	}

	public static void main(String[] args) {
		char[] characters = {'a', 'd', 'b', 'e', 'f'};
		int[] frequencies = {10, 50, 20, 40, 80};
		
		HauffmanCompressionEncoding hauffmanCompressionEncoding = new HauffmanCompressionEncoding();
		hauffmanCompressionEncoding.generateHuffmanCodes(characters, frequencies);
	}
	
	class Node implements Comparable<Node> {
		char character;
		int frequency;
		Node left;
		Node right;
		
		public Node(char character, int frequency) {
			this.character = character;
			this.frequency = frequency;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [character=");
			builder.append(character);
			builder.append(", frequency=");
			builder.append(frequency);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(Node o) {
			return this.frequency - o.frequency;
		}
		
	}
	
}

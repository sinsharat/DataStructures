package com.sharat.datastructures.all.leetcode;

public class Add1ToANumberRepresentedAsLinkedList {

	class Node {
		int data;
		Node next;

		Node(int x) {
			data = x;
			next = null;
		}
	}

	public Node addOne(Node head) {
		Node reverseNode = reverseNode(head);

		Node result = addOneToNode(reverseNode);
		result = reverseNode(reverseNode);

		return result;
	}

	private Node reverseNode(Node node) {
		Node prevNode = null;
		Node curNode = null;
		while (node != null) {
			curNode = new Node(node.data);
			if (prevNode != null) {
				curNode.next = prevNode;
			}
			prevNode = curNode;
			node = node.next;
		}

		return curNode;
	}

	private Node addOneToNode(Node node) {
		Node head = node;
		
		int carry = 1;
		int sum;
		Node prev = null;
		while (node != null) {
			sum = node.data + carry;
			carry = sum/10;
			node.data = sum%10;
			prev = node;
			node = node.next;
		}
		
		if (prev == null) {
			return new Node(1);
		}
		if (carry != 0) {
			prev.next = new Node(carry);
		}
		
		return head;
	}
	
	public static void main(String []args) { 
		Add1ToANumberRepresentedAsLinkedList add = new Add1ToANumberRepresentedAsLinkedList();
		Node input = add.new Node(299);
		
		Node output = add.addOne(input);
		while (output != null) {
			System.out.print(output.data);
			output = output.next;
		}
		
	}
}

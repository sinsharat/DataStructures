/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures;

/**
 * Test.java
 *
 * @author S00742735
 * @since 2020-10-06
 */
public class SortAndMergeArrayOfLinkedListIntoSingleSortedLinkedList {

	static class Node {
		int val;
		Node next;

		Node() {
		}

		Node(int val) {
			this.val = val;
		}

		Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
	}

	public Node mergeKLists(Node[] lists) {
		if (null == lists || lists.length == 0) {
			return null;
		}

		int listLength = lists.length;
		Node result = lists[0];
		Node finalNode = result;
		Node itrNode = null;
		Node prevNode = null;
		Node tempNode = null;
		for (int i = 1; i < listLength; i++) {
			finalNode = result;
			itrNode = lists[i];
			prevNode = null;
			while (finalNode != null && itrNode != null) {
				if (finalNode.val > itrNode.val) {
					tempNode = finalNode;
					if (null == prevNode) {
						finalNode = itrNode;
						itrNode.next = tempNode;
					} else {
						prevNode.next = itrNode;
						itrNode.next = finalNode;
					}

				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Node[] listNodes = new Node[3];
		listNodes[0] = new Node(1);
		listNodes[0].next = new Node(4);
		listNodes[0].next.next = new Node(5);
		listNodes[1] = new Node(1);
		listNodes[1].next = new Node(3);
		listNodes[1].next.next = new Node(4);
		listNodes[2] = new Node(2);
		listNodes[2].next = new Node(6);

		SortAndMergeArrayOfLinkedListIntoSingleSortedLinkedList t = new SortAndMergeArrayOfLinkedListIntoSingleSortedLinkedList();
		Node result = t.mergeKLists(listNodes);
		System.out.println(result.val);
		while ((result = result.next) != null) {
			System.out.println(result.val);
		}
	}
}

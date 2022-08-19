package com.sharat.datastructures.all.leetcode;

public class LinkedListIntersection {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		// boundary check
		if (headA == null || headB == null) {
			return null;
		}

		ListNode a = headA;
		ListNode b = headB;

		// if a & b have different len, then we will stop the loop after second
		// iteration
		while (a != b) {
			// for the end of first iteration, we just reset the pointer to the head of
			// another linkedlist
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}

		return a;
	}

	public static void main(String[] args) {
		LinkedListIntersection lli = new LinkedListIntersection();
		ListNode ln1 = lli.new ListNode(1);
		ln1.next = lli.new ListNode(2);
		ListNode lnr = lli.new ListNode(3);
		ln1.next.next = lnr;
		ln1.next.next.next = lli.new ListNode(4);
		ln1.next.next.next.next = lli.new ListNode(5);

		ListNode ln2 = lli.new ListNode(6);
		ln2.next = lli.new ListNode(7);
		ln2.next.next = lli.new ListNode(8);
		ln2.next.next.next = lnr;

		ListNode result = lli.getIntersectionNode(ln1, ln2);
		System.out.println(result == null ? null : result.val);

		ln1 = lli.new ListNode(1);
		ln1.next = lli.new ListNode(2);
		ln1.next.next = lli.new ListNode(3);
		;
		ln1.next.next.next = lli.new ListNode(4);
		ln1.next.next.next.next = lli.new ListNode(5);

		ln2 = lli.new ListNode(6);
		ln2.next = lli.new ListNode(7);
		ln2.next.next = lli.new ListNode(8);

		result = lli.getIntersectionNode(ln1, ln2);
		System.out.println(result == null ? null : result.val);
	}

}

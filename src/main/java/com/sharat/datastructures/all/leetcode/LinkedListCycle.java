package com.sharat.datastructures.all.leetcode;

public class LinkedListCycle {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public boolean hasCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		LinkedListCycle llc = new LinkedListCycle();
		ListNode ln = llc.new ListNode(1);
		ListNode ln1 = llc.new ListNode(2);
		ln.next = ln1;
		ln.next.next = llc.new ListNode(3);
		ln.next.next.next = llc.new ListNode(4);
		ln.next.next.next.next = ln1;
		System.out.println(llc.hasCycle(ln));
		ln = llc.new ListNode(1);
		ln.next = llc.new ListNode(2);
		ln.next.next = llc.new ListNode(3);
		ln.next.next.next = llc.new ListNode(4);
		System.out.println(llc.hasCycle(ln));
	}
}

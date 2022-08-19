package com.sharat.datastructures.all.leetcode;

import java.util.StringJoiner;

public class AddTwoNosInReverseLinkedList {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;
            
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
	}
	
	public static void main(String []args) {
		AddTwoNosInReverseLinkedList atn = new AddTwoNosInReverseLinkedList();
		
		ListNode l1 = new ListNode(9);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(9);
		l2.next.next.next = new ListNode(9);
		l2.next.next.next.next = new ListNode(9);
		l2.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next.next.next = new ListNode(9);
		ListNode res = atn.addTwoNumbers(l1, l2);
		StringJoiner joiner = new StringJoiner("," , "[", "]");
		while (res != null) {
			joiner.add(res.val + "");
			res = res.next;
		}
		System.out.println(joiner.toString());
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

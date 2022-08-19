package com.sharat.datastructures.all.leetcode;

public class MajorityElement {

	public int majorityElement(int[] nums) {
		int n = nums.length;
		int major = nums[0];
		int count = 1;
		for (int i = 1; i < n; i++) {
			if (count == 0) {
				count++;
				major = nums[i];
			} else if (major == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}

	public static void main(String[] args) {
		MajorityElement me = new MajorityElement();
		System.out.println(me.majorityElement(new int[] { 8, 8, 7, 7, 7 }));
		System.out.println(me.majorityElement(new int[] { 10, 9, 9, 9, 10 }));
	}

}

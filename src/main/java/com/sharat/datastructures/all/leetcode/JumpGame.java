package com.sharat.datastructures.all.leetcode;

public class JumpGame {

	public boolean canJump(int[] nums) {

		int n = nums.length;
		int last = n - 1, i;
		for (i = n - 2; i >= 0; i--) {
			if (i + nums[i] >= last) {
				last = i;
			}
		}
		return last <= 0;
	}

	public static void main(String[] args) {
		JumpGame jg = new JumpGame();
		System.out.println(jg.canJump(new int[] { 3, 2, 1, 0, 4 }));
	}

}

package com.sharat.datastructures.all.leetcode;

public class MaxTrapWater {

	public int trap(int[] height) {
		int n = height.length;
		if (n == 1) {
			return 0;
		}

		int[] leftMax = new int[n];
		leftMax[0] = height[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}

		int[] rightMax = new int[n];
		rightMax[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}

		int[] maxMin = new int[n];
		for (int i = 0; i < n; i++) {
			maxMin[i] = Math.min(leftMax[i], rightMax[i]);
		}

		int maxTrap = 0;
		for (int i = 0; i < n; i++) {
			maxTrap += maxMin[i] - height[i];
		}

		return maxTrap;
	}

	public static void main(String[] args) {
		MaxTrapWater mtw = new MaxTrapWater();
		System.out.println(mtw.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
	}

}

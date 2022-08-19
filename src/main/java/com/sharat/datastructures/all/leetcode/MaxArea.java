package com.sharat.datastructures.all.leetcode;

public class MaxArea {

	public int maxArea(int[] height) {

		int n = height.length;
		int maxValue = Integer.MIN_VALUE;
		int i = 0, j = n - 1;
		int leftHeight;
		int rightHeight;
		while (i < j) {
			leftHeight = height[i];
			rightHeight = height[j];

			maxValue = Math.max(maxValue, Math.min(leftHeight, rightHeight) * (j - i));

			if (leftHeight < rightHeight) {
				i++;
			} else {
				j--;
			}
		}

		return maxValue;

	}

	public static void main(String[] args) {
		MaxArea ma = new MaxArea();
		System.out.println(ma.maxArea(new int[] { 2, 3, 10, 5, 7, 8, 9 }));
	}
}
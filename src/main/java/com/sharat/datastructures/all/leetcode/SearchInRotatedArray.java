package com.sharat.datastructures.all.leetcode;

public class SearchInRotatedArray {

	public int search(int[] nums, int target) {
		int i = 0;
		int j = nums.length;
		int mid;
		while (i < j) {
			mid = (i + j) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[i] < nums[mid]) {
				if (nums[i] <= target && nums[mid] > target) {
					j = mid;
				} else {
					i = mid + 1;
				}
			} else {
				if (nums[i] <= target || nums[mid] > target) {
					j = mid;
				} else {
					i = mid + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SearchInRotatedArray sisa = new SearchInRotatedArray();
		System.out.println(sisa.search(new int[] { 6, 7, 0, 1, 2, 3, 4, 5 }, 0));
	}

}

package com.sharat.datastructures.all.leetcode;

public class MedianOfTwoSortedArrays {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		int medIndex1, medIndex2;
		int total = m + n;
		if (total % 2 == 0) {
			medIndex2 = total / 2;
			medIndex1 = medIndex2 - 1;
		} else {
			medIndex1 = medIndex2 = total / 2;
		}

		int start1 = 0;
		int start2 = 0;
		int count = 0;
		int val1 = Integer.MIN_VALUE, val2 = Integer.MIN_VALUE;
		while (start1 < m && start2 < n && count <= medIndex2) {
			if (medIndex1 == count) {
				val1 = nums1[start1] <= nums2[start2] ? nums1[start1] : nums2[start2];
			}
			if (medIndex2 == count) {
				val2 = nums1[start1] <= nums2[start2] ? nums1[start1] : nums2[start2];
			}

			if (nums1[start1] <= nums2[start2]) {
				start1++;
			} else {
				start2++;
			}
			count++;
		}

		if (val1 != Integer.MIN_VALUE && val2 != Integer.MIN_VALUE) {
			return ((double) val1 + (double) val2) / 2;
		}

		while (start1 < m && count <= medIndex2) {
			if (medIndex1 == count) {
				val1 = nums1[start1];
			}
			if (medIndex2 == count) {
				val2 = nums1[start1];
			}
			start1++;
			count++;
		}

		while (start2 < n && count <= medIndex2) {
			if (medIndex1 == count) {
				val1 = nums2[start2];
			}
			if (medIndex2 == count) {
				val2 = nums2[start2];
			}
			start2++;
			count++;
		}

		return ((double) val1 + (double) val2) / 2;
	}

	public static void main(String[] args) {
		MedianOfTwoSortedArrays motsa = new MedianOfTwoSortedArrays();
		int[] nums1 = new int[] { 3, 4 };
		int[] nums2 = new int[] {};
		System.out.println(motsa.findMedianSortedArrays(nums1, nums2));
	}

}

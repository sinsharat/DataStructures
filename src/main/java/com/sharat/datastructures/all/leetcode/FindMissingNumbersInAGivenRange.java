package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindMissingNumbersInAGivenRange {

	public List<Integer> findMissingRanges(int[] nums, int low, int high) {
		List<Integer> missingNos = new ArrayList<>();
		Set<Integer> hs = new HashSet<>();

		int n = nums.length;
		// Insert all elements of arr[] in set
		for (int i = 0; i < n; i++)
			hs.add(nums[i]);

		// Traverse through the range an print all
		// missing elements
		for (int i = low; i <= high; i++) {
			if (!hs.contains(i)) {
				missingNos.add(i);
			}
		}

		return missingNos;
	}

	public static void main(String[] args) {
		FindMissingNumbersInAGivenRange findMissingNumbersInAGivenRange = new FindMissingNumbersInAGivenRange();
		System.out.println(findMissingNumbersInAGivenRange.findMissingRanges(new int[] { 10, 12, 11, 15 }, 10, 15));
		System.out.println(findMissingNumbersInAGivenRange.findMissingRanges(new int[] { 1, 14, 11, 51, 15 }, 50, 55));
	}

}

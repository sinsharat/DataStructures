package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

	public int[][] merge(int[][] intervals) {

		int n = intervals.length;

		Arrays.sort(intervals, (n1, n2) -> {
			if (n1[0] == n2[0]) {
				return n1[1] - n2[1];
			} else {
				return n1[0] - n2[0];
			}
		});

		List<int[]> result = new ArrayList<>();

		int i = 0;
		int start;
		int end;
		while (i < n) {
			start = intervals[i][0];
			end = intervals[i][1];
			while (i < n - 1 && end >= intervals[i + 1][0]) {
				if (end <= intervals[i + 1][1]) {
					end = intervals[i + 1][1];
					i++;
				} else {
					i++;
				}
			}
			result.add(new int[] { start, end });
			i++;
		}

		return result.toArray(new int[result.size()][2]);
	}

	public static void main(String[] args) {
		MergeIntervals mi = new MergeIntervals();
		int[][] result = mi.merge(new int[][] {{1,4},{4,5}});
		for (int[] interval : result) {
			System.out.println(Arrays.toString(interval));
		}
	}

}

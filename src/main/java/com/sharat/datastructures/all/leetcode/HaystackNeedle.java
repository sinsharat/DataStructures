package com.sharat.datastructures.all.leetcode;

public class HaystackNeedle {

	public int strStr(String haystack, String needle) {
		int m = haystack.length();
		int n = needle.length();
		int count;
		for (int i = 0; i < m; i++) {
			count = 0;
			for (int j = 0; j < n; j++) {
				if (i + j == m) {
					return -1;
				} else if (needle.charAt(j) != haystack.charAt(i + j)) {
					break;
				} else {
					count++;
				}
			}
			if (count == n) { 
				return i;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {
		HaystackNeedle hn = new HaystackNeedle();
		System.out.println(hn.strStr("hello", "ll"));

	}
}

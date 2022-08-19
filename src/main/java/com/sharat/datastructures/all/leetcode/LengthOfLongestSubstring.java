package com.sharat.datastructures.all.leetcode;

public class LengthOfLongestSubstring {

	public int lengthOfLongestSubstring(String s) {

		int n = s.length();
		String str = "";
		int maxLength = 0;
		String c;
		for (int i = 0; i < n; i++) {
			c = s.charAt(i) + "";
			if (str.contains(c)) {
				maxLength = Math.max(maxLength, str.length());
				str = str.substring(str.indexOf(c) + 1);
			}
			str += c;
		}
		maxLength = Math.max(maxLength, str.length());
		return maxLength;
	}

	public static void main(String[] args) {
		LengthOfLongestSubstring lss = new LengthOfLongestSubstring();
		System.out.println(lss.lengthOfLongestSubstring("aab"));
	}
}

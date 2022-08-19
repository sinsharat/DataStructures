package com.sharat.datastructures.all.leetcode;

public class Atio {

	public int myAtoi(String s) {
		int index = 0;
		int n = s.length();

		while (index < n && s.charAt(index) == ' ') {
			index++;
		}

		String nums = "";
		if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
			nums = s.charAt(index) + "";
			index++;
		}

		while (index < n && s.charAt(index) == '0') {
			index++;
		}

		String validNums = "0123456789";
		while (index < n && validNums.contains(s.charAt(index) + "")) {
			nums += s.charAt(index);
			index++;
		}

		if (nums.equals("") || nums.equals("+") || nums.equals("-")) {
			nums = "0";
		}

		if (nums.length() > 11) {
			if (nums.startsWith("-")) {
				return Integer.MIN_VALUE;
			} else {
				return Integer.MAX_VALUE;
			}
		}

		long val = Long.parseLong(nums);

		if (val < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else if (val > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return (int) val;
		}
	}

	public static void main(String[] args) {
		Atio at = new Atio();
		System.out.println(at.myAtoi(null));
	}

}

package com.sharat.datastructures.all.leetcode;

public class ReverseInteger {

	public int reverse(int x) {
		int result = 0;

		int tail;
		int newResult;
		while (x != 0) {
			tail = x % 10;
			newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result) {
				return 0;
			}
			result = newResult;
			x = x / 10;
		}

		return result;
	}

	public static void main(String[] args) {
		ReverseInteger ri = new ReverseInteger();
		System.out.println(ri.reverse(1534236469));
	}

}

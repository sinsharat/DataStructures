package com.sharat.datastructures.all.leetcode;

public class SquareRootOfNumber {

	public int mySqrt(int x) {
		if (x < 2) {
			return x;
		}

		int i = 1;
		int j = Integer.MAX_VALUE;
		int mid;
		while (i < j) {
			mid = i + (j - i) / 2;
			if (mid == x / mid || mid < x / mid && (mid + 1) > x / (mid + 1)) {
				return mid;
			} else if (mid < x && mid < x / mid) {
				i = mid + 1;
			} else {
				j = mid;
			}
		}

		return i;
	}

	public static void main(String[] args) {
		SquareRootOfNumber sqrt = new SquareRootOfNumber();
		System.out.println(sqrt.mySqrt(2147395599));

	}
}

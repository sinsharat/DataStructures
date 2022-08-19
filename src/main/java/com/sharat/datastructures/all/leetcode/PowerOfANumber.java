package com.sharat.datastructures.all.leetcode;

public class PowerOfANumber {

	public double myPow(double x, int n) {

		if (n == 0) {
			return 1;
		}

		if (n < 0) {
			x = 1 / x;
		}
		return (n % 2 == 0) ? myPow(x * x, n < 0 ? -(n / 2) : n / 2) : x * myPow(x * x, n < 0 ? -(n / 2) : n / 2);
	}

	public static void main(String[] args) {
		PowerOfANumber pon = new PowerOfANumber();
		System.out.println(pon.myPow(2.00000, 10));
	}
}

package com.sharat.ds.recursion;

public class Factorial {
	
	public int getFactorial(int num) {
		if (num == 1) {
			return 1;
		} else {
			return num * getFactorial(num - 1);
		}
	}
	
	public static void main (String []args) {
		Factorial fc = new Factorial();
		System.out.println(fc.getFactorial(5));
	}

}

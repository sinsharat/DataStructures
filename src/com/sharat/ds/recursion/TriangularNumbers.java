package com.sharat.ds.recursion;

public class TriangularNumbers {

	public int getTringleSquares(int num) {
		if (num == 1) {
			return 1;
		} else {
			return (num + getTringleSquares(num - 1));
		}
	}
	
	public static void main(String []args) {
		TriangularNumbers tn = new TriangularNumbers();
	    System.out.println(tn.getTringleSquares(5));
	}
	
}

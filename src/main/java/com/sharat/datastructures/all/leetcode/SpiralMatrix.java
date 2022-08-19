package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();

		int m = matrix.length;
		int n = matrix[0].length;

		int totalNums = m * n;
		int count = 0;
		int a = 0;
		int b = 0;
		while (count < totalNums) {
			for (int j = a; j < n - a; j++) {
				result.add(matrix[a][j]);
				count++;
			}

			if (count == totalNums) {
				return result;
			}

			for (int i = b + 1; i < m - b; i++) {
				result.add(matrix[i][n - b - 1]);
				count++;
			}

			if (count == totalNums) {
				return result;
			}

			for (int j = n - a - 2; j >= a; j--) {
				result.add(matrix[m - a - 1][j]);
				count++;
			}

			if (count == totalNums) {
				return result;
			}

			for (int i = m - b - 2; i > b; i--) {
				result.add(matrix[i][b]);
				count++;
			}

			a++;
			b++;
		}

		return result;
	}

	public static void main(String[] args) {
		SpiralMatrix sm = new SpiralMatrix();
		System.out.println(sm.spiralOrder(new int[][] { { 1, 2, 3, 4 }, 
			                                            { 5, 6, 7, 8 }, 
			                                            { 9, 10, 11, 12 },
				                                        { 13, 14, 15, 15 }, 
				                                        { 16, 17, 18, 19 } 
				                                      }));
	}
}

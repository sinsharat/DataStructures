package com.sharat.datastructures.all.leetcode;

import java.util.Arrays;

public class SetMaxZeroes {

	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		boolean fr = false;
		boolean fc = false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != 0) {
					continue;
				}

				if (i == 0) {
					fr = true;
				}

				if (j == 0) {
					fc = true;
				}

				matrix[0][j] = 0;
				matrix[i][0] = 0;
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		if (fr) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}

		if (fc) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

	public static void main(String[] args) {
		SetMaxZeroes smz = new SetMaxZeroes();
		int[][] matrix = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		smz.setZeroes(matrix);
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}

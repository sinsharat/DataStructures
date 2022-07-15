package com.sharat.datastructures.backtracking;

import java.util.Arrays;

public class NQueenProblem {

	// check if possible to place n queens in a matrix of nxn
	// the queens should be placed in such a way that they are not
	// attackable by each other i.e. not in same line or in diagonals to each other
	public boolean checkAndPrintPosIfQueenPlacementPossible(int noOfQueens) {
		int[][] queenPosRes = new int[noOfQueens][noOfQueens];
		if (!checkAndPrintPosIfQueenPlacementPossibleRec(noOfQueens, 0, queenPosRes)) {
			return false;
		}

		for (int[] rows : queenPosRes) {
			System.out.println(Arrays.toString(rows));
		}
		return true;
	}

	private boolean checkAndPrintPosIfQueenPlacementPossibleRec(int noOfQueens, int col, int[][] queenPosRes) {
		if (col == noOfQueens) {
			return true;
		}

		for (int row = 0; row < noOfQueens; row++) {
			if (isSafe(noOfQueens, row, col, queenPosRes)) {
				queenPosRes[row][col] = 1;
				if (checkAndPrintPosIfQueenPlacementPossibleRec(noOfQueens, col + 1, queenPosRes)) {
					return true;
				} else {
					queenPosRes[row][col] = 0;
				}
			}
		}

		return false;
	}

	private boolean isSafe(int noOfQueens, int row, int col, int[][] queenPosRes) {

		// row check
		for (int j = 0; j < col; j++) {
			if (queenPosRes[row][j] == 1) {
				return false;
			}
		}

		// left diagonal check
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (queenPosRes[i][j] == 1) {
				return false;
			}
		}

		// right diagonal check
		for (int i = row, j = col; j >= 0 && i < noOfQueens; i++, j--) {
			if (queenPosRes[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		NQueenProblem nQueenProblem = new NQueenProblem();
		int noOfQueens = 4;
		System.out.println("Result of nQueenProblem for noOfQueens " + noOfQueens + ":");
		boolean result = nQueenProblem.checkAndPrintPosIfQueenPlacementPossible(noOfQueens);
		System.out.println(result);

		noOfQueens = 3;
		System.out.println("Result of nQueenProblem for noOfQueens " + noOfQueens + ":");
		result = nQueenProblem.checkAndPrintPosIfQueenPlacementPossible(noOfQueens);
		System.out.println(result);

	}

}

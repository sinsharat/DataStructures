package com.sharat.datastructures.backtracking;

import java.util.Arrays;

public class RatInAMaze {

	public boolean findPathForRatInMazeToCheese(int[][] maze) {
		if (maze == null) {
			return false;
		}

		int[][] mazeSol = new int[maze.length][maze.length];

		if (!solveMazeRec(maze, 0, 0, maze.length, mazeSol)) {
			return false;
		}

		for (int[] x : mazeSol) {
			System.out.println(Arrays.toString(x));
		}
		return true;
	}

	private boolean solveMazeRec(int[][] maze, int i, int j, int mazeSize, int[][] mazeSol) {
		if (i == mazeSize - 1 && j == mazeSize - 1 && maze[i][j] == 1) {
			mazeSol[i][j] = 1;
			return true;
		}
		if (isSafe(maze, i, j, mazeSize)) {
			mazeSol[i][j] = 1;
			if (solveMazeRec(maze, i + 1, j, mazeSize, mazeSol)) {
				return true;
			} else if (solveMazeRec(maze, i, j + 1, mazeSize, mazeSol)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSafe(int[][] maze, int i, int j, int maxSize) {
		if (i < maxSize && j < maxSize && maze[i][j] == 1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		RatInAMaze riam = new RatInAMaze();
		int[][] maze1 = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
		System.out.println(riam.findPathForRatInMazeToCheese(maze1));

		int[][] maze2 = { { 1, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
		System.out.println(riam.findPathForRatInMazeToCheese(maze2));
	}
}

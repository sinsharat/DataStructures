package com.sharat.datastructures.sorting;

import java.util.Arrays;

public class ShellSort {

	public int[] sort(int[] a)
	{
		int temp, j, count = 0, arraySize = 0;
		if (null == a || (arraySize = a.length) <= 1) {
			return a;
		}

		int h = 1;
		while (h <= arraySize / 3) {
			h = (h * 3) + 1;
		}

		while (h > 0) {
			for (int i = h; i < arraySize; i++) {
				temp = a[i];
				for (j = i; (j > h - 1 && a[j - h] >= temp); j -= h) {
					count++;
					a[j] = a[j - h];
				}
				a[j] = temp;
			}
			h = (h - 1)/3;
		}

		System.out.println("Array size : " + arraySize + ", comparisons : " + count);
		return a;
	}
	
	public static void main(String[] args)
	{
		int[] unsortedArray = {9,8,7,1,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		ShellSort is = new ShellSort();
		System.out.println("Sorted Array : " + Arrays.toString(is.sort(unsortedArray)));
	}

}

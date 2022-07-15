package com.sharat.datastructures.sorting;

import java.util.Arrays;

public class InsertionSort {

	public void sort(int[] a)
	{
		int arraySize, temp, j, count = 0;
		if (null == a || (arraySize = a.length) <= 1) {
			return;
		}
		
		for (int i = 1; i < arraySize; i++) {
			temp = a[i];
			for (j = i; j > 0 && a[j - 1] >= temp; j--) {
				count++;
				a[j] = a[j - 1];
			}
			a[j] = temp;
		}
		
		System.out.println("Array size : " + arraySize + ", comparisons : " + count);
	}
	
	public static void main(String[] args)
	{
		int[] unsortedArray = {8,7,1,9,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		InsertionSort is = new InsertionSort();
		is.sort(unsortedArray);
		System.out.println("Sorted Array : " + Arrays.toString(unsortedArray));
	}

}

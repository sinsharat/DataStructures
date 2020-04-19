package com.sharat.ds.sorting;

import java.util.Arrays;

public class SelectionSort {

	public void sort(int[] a)
	{
		int arraySize, temp, min, count = 0;
		if (null != a && (arraySize = a.length) > 0) {
			for (int i = 0; i < arraySize - 1; i++) {
				min = i;
				for (int j = i+1; j < arraySize; j++) {
					count++;
					if (a[min] > a[j]) {
						min = j;
					}
				}
				temp = a[min];
				a[min] = a[i];
				a[i] = temp;
			}
			System.out.println("Array size : " + arraySize + ", comparisons : " + count);
		}
	}
	
	public static void main(String[] args)
	{
		int[] unsortedArray = {8,7,1,9,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		SelectionSort ss = new SelectionSort();
		ss.sort(unsortedArray);
		System.out.println("Sorted Array : " + Arrays.toString(unsortedArray));
	}

}

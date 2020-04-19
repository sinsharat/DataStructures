package com.sharat.ds.sorting;

import java.util.Arrays;

public class BubbleSort {
	
	public void sort(int[] a) {
		
		int arraySize, temp, count = 0;
		if (null != a && (arraySize = a.length) > 1) {
			for (int i= arraySize - 1; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					count++;
					if (a[j] > a[j+1]) {
						temp = a[j];
						a[j] = a[j + 1];
						a[j + 1] = temp;
					}
				}
			}
			System.out.println("array size : " + arraySize + ", comparisons : " + count);
		}
	}
	
	public static void main(String[] args) {
		int[] unsortedArray = {8,7,1,9,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		BubbleSort bs = new BubbleSort();
		bs.sort(unsortedArray);
		System.out.println("Sorted Array : " + Arrays.toString(unsortedArray));
	}
}

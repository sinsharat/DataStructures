package com.sharat.datastructures.sorting;

import java.util.Arrays;

public class MergeSort {
	
	private int[] resultArray;
	
	public int[] doMergeSort(int[] inputArray) {
		int size;
		if (null == inputArray || (size = inputArray.length) <= 1) {
			return inputArray;
		}
		resultArray = inputArray;
		int[] processArray = new int[size];
		performMergeSort(processArray, 0, size - 1);
		return this.resultArray;
	}

	private void performMergeSort(int[] processArray, int begin, int end)
	{
		if (begin == end) {
			return;
		}
		int mid = (begin + end)/2;
		performMergeSort(processArray, begin, mid);
		performMergeSort(processArray, mid + 1, end);
		
		merge(processArray, begin, mid, end);
		
	}
	
	private void merge(int[] processArray, int begin, int mid, int end) {
		int i = 0;
		int lowerBound = begin;
		int totalCount = end - begin + 1;
		int higherStart = mid + 1;
		while(begin <= mid && higherStart <= end) {
			if (resultArray[begin] <= resultArray[higherStart]) {
				processArray[i] = resultArray[begin];
				i++; begin++;
			} else {
				processArray[i] = resultArray[higherStart];
				i++; higherStart++;
			}
		}
		
		while (begin <= mid) {
			processArray[i] = resultArray[begin];
			i++; begin++;
		}
		
		while (higherStart <= end) {
			processArray[i] = resultArray[higherStart];
			i++; higherStart++;
		}
		
		for (int j=0; j < totalCount; j++) {
			resultArray[lowerBound + j] = processArray[j];
		}
	}
	
	public static void main(String[] args)
	{
		int[] unsortedArray = {9,8,7,1,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		MergeSort ms = new MergeSort();
		System.out.println("Sorted Array : " + Arrays.toString(ms.doMergeSort(unsortedArray)));
	}

}

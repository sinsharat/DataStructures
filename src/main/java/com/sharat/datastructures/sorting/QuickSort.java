package com.sharat.datastructures.sorting;

import java.util.Arrays;

public class QuickSort {

	private int[] processArray;
	
	private int[] sort(int[] unsortedArray) {
		this.processArray = unsortedArray;
		int length = 0;
		if (null != processArray && (length = processArray.length) > 1) {
			int leftIndex = 0;
			int rightIndex = length - 1;
			quickSort(leftIndex, rightIndex);
			return processArray;
		} else {
			return processArray;
		}
	}

	private void quickSort(int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex) {
			return;
		} else {
			int pivot = processArray[rightIndex];
			int partitionPoint = partition(leftIndex, rightIndex, pivot);
			quickSort(leftIndex, partitionPoint - 1);
			quickSort(partitionPoint + 1, rightIndex);
		}
	}

	private int partition(int leftIndex, int rightIndex, int pivot) {
		int leftPointer = leftIndex - 1;
		int rightPointer = rightIndex;

		while (true) {
			// run the loop till it finds a value greater than or equal to pivot value
			while (processArray[++leftPointer] < pivot) {
				// do nothing
			}
			// run the loop till it finds a value less than or equal to pivot value
			while (rightPointer > 0 && processArray[--rightPointer] > pivot) {
				// do nothing
			}
			if (leftPointer >= rightPointer) {
				break;
			} else {
				swap(leftPointer, rightPointer);
			}
		}
		swap(leftPointer, rightIndex);
		System.out.println("Updated Array: " + Arrays.toString(processArray));
		return leftPointer;
	}
	
	private void swap(int leftIndex, int rightIndex) {
		int temp = processArray[rightIndex];
		processArray[rightIndex] = processArray[leftIndex];
		processArray[leftIndex] = temp;
	}

	public static void main(String[] args) {
		int[] unsortedArray = { 9, 8, 7, 1, 6, 3, 7, 5, 0, 3, 5, 2 };
		System.out
				.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		QuickSort qs = new QuickSort();
		System.out.println("Sorted Array : "
				+ Arrays.toString(qs.sort(unsortedArray)));
	}

}

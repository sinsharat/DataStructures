package com.sharat.datastructures.sorting;

import java.util.Arrays;

public class QuickSort2 {

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
		int size = rightIndex - leftIndex + 1;
		if (size <= 3) {
			manualSort(leftIndex, rightIndex);
		} else {
			int pivot = medianOf3(leftIndex, rightIndex);
			int partitionPoint = partition(leftIndex, rightIndex, pivot);
			quickSort(leftIndex, partitionPoint - 1);
			quickSort(partitionPoint + 1, rightIndex);
		}
	}

	private void manualSort(int left, int right)
	{
		int size = right - left + 1;
		if (size <= 1)
			return; // no sort necessary
		if (size == 2) { // 2-sort left and right
			if (processArray[left] > processArray[right])
				swap(left, right);
			return;
		} else {   // 3-sort left, center, & right
			if (processArray[left] > processArray[right - 1])
				swap(left, right - 1); // left, center
			if (processArray[left] > processArray[right])
				swap(left, right); // left, right
			if (processArray[right - 1] > processArray[right])
				swap(right - 1, right); // center, right
		}
	}
	
	public int medianOf3(int left, int right) {
		int center = (left + right) / 2;
		// order left & center
		if (processArray[left] > processArray[center])
			swap(left, center);
		// order left & right
		if (processArray[left] > processArray[right])
			swap(left, right);
		// order center & right
		if (processArray[center] > processArray[right])
			swap(center, right);
		swap(center, right - 1); // put pivot on right
		return processArray[right - 1]; // return median value
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
		QuickSort2 qs = new QuickSort2();
		System.out.println("Sorted Array : "
				+ Arrays.toString(qs.sort(unsortedArray)));
	}

}

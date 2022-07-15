package com.sharat.datastructures.sorting;

//Radix sort Java implementation
import java.util.Arrays;

public class RadixSort {

	// The main function to that sorts arr[] of size n using
	// Radix Sort
	public void sort(int arr[]) {
		int length = arr.length;
		// Find the maximum number to know number of digits
		int m = getMax(arr, length);

		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. exp is 10^i
		// where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10) {
			countSort(arr, length, exp);
		}
	}

	// A utility function to get maximum value in arr[]
	private int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > mx) {
				mx = arr[i];
			}
		}
		return mx;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	private void countSort(int arr[], int n, int exp) {
		int output[] = new int[n]; // output array
		int count[] = new int[10]; // keep count of values occurance count
		Arrays.fill(count, 0);

		int valueDigit; // get the digit a an index for the value of the specified index
		
		// Store count of occurrences in count[]
		for (int i = 0; i < n; i++) {
			valueDigit = (arr[i] / exp) % 10;
			count[valueDigit]++;
		}
		
		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (int i = 1; i < 10; i++) {
			// sum up the previous count for a specific digit to find the index position for each digit.
			// For example if 1 occured 2 times and 2 occured 1 time and 3 occured 2 times 
			// then index for 1 will be 2(2 and 1 for 2 occurences of 1), for 2 index will be 3 and for 3 index will be 5(5 and 4 for decrement).
			count[i] += count[i - 1];  
		}

		// Build the output array
		for (int i = n - 1; i >= 0; i--) {
			valueDigit = (arr[i] / exp) % 10;
			output[count[valueDigit] - 1]  = arr[i];
			count[valueDigit]--; // decrement the index value so that duplicate digit get the immediate previous from the one specified just now. 
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	/* Driver function to check for above function */
	public static void main(String[] args) {
		int[] unsortedArray = { 9, 8, 7, 1, 6, 3, 7, 5, 0, 3, 5, 2 };
		System.out
				.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		RadixSort rs = new RadixSort();
		rs.sort(unsortedArray);
		System.out.println("Sorted Array : "
				+ Arrays.toString(unsortedArray));
	}
}
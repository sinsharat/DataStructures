package com.sharat.datastructures.heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class SortKSortedArray {
	
	/**
	 * sort an array which has its element in k proximity.
	 * @param arr arr of unsorted elements
	 * @param k number range in which sorted position is present
	 * @return sorted array
	 */
	public int[] sortKSortedArray(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i <= k; i++) {
			pq.add(arr[i]);
		}
		
		int startIndex = 0;
		for (int i=k+1; i < arr.length; i++) {
			arr[startIndex++] = pq.poll();
			pq.add(arr[i]);
		}
		
		while(!pq.isEmpty()) {
			arr[startIndex++] = pq.poll();
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		SortKSortedArray sortKSortedArray = new SortKSortedArray();
		
		int[] inp1 = {9, 8, 7, 18, 19, 17};
		int k1 = 2;
		int[] sortedArr = sortKSortedArray.sortKSortedArray(inp1, k1);
		List<Object> sortedList = Arrays.stream(sortedArr).boxed().collect(Collectors.toList());
		System.out.println(sortedList);
		
		int[] inp2 = {10, 9,7, 8, 4, 70, 50, 60};
		int k2 = 4;
		sortedArr = sortKSortedArray.sortKSortedArray(inp2, k2);
		sortedList = Arrays.stream(sortedArr).boxed().collect(Collectors.toList());
		System.out.println(sortedList);
	}

}

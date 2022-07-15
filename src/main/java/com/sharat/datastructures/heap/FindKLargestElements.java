package com.sharat.datastructures.heap;

import java.util.PriorityQueue;

public class FindKLargestElements {
	
	public void printKLargestElements(int[] arr, int noOfLargestElements) {
		if (arr.length < noOfLargestElements) {
			noOfLargestElements = arr.length;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < noOfLargestElements; i++) {
			pq.add(arr[i]);
		}
		
		for (int i = noOfLargestElements; i < arr.length; i++) {
			if (pq.peek() < arr[i]) {
				pq.poll();
				pq.add(arr[i]);
			}
		}
		
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
	}
	
	public static void main(String []args) {
		FindKLargestElements findKLargestElements = new FindKLargestElements();
		
		int[] input1 = {5, 15, 10, 20, 8};
		int noOfLargestElements = 2;
		findKLargestElements.printKLargestElements(input1, noOfLargestElements);
		System.out.println();
		int[] input2 = {8, 6, 4, 10, 9};
		noOfLargestElements = 3;
		findKLargestElements.printKLargestElements(input2, noOfLargestElements);
	}

}

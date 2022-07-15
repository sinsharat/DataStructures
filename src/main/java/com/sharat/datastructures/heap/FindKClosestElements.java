package com.sharat.datastructures.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindKClosestElements {

	class CustomDataMap implements Comparable<CustomDataMap> {
		
		private int key;
		
		private int value;
		
		public CustomDataMap(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("CustomDataMap [key=");
			builder.append(key);
			builder.append(", value=");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(CustomDataMap o) {
			return this.key - o.getKey();
		}
		
	}

	public void getKClosestElements(int[] arr, int number, int noOfCloseElementsToFind) {
		if (arr.length < noOfCloseElementsToFind) {
			noOfCloseElementsToFind = arr.length;
		}

		PriorityQueue<CustomDataMap> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < noOfCloseElementsToFind; i++) {
			pq.add(new CustomDataMap(Math.abs(arr[i] - number), arr[i]));
		}

		for (int i = noOfCloseElementsToFind; i < arr.length; i++) {
			if (pq.peek().getKey() > Math.abs(arr[i] - number)) {
				pq.poll();
				pq.add(new CustomDataMap(Math.abs(arr[i] - number), arr[i]));
			}
		}

		while (!pq.isEmpty()) {
			System.out.print(pq.poll().getValue() + " ");
		}
	}

	public static void main(String[] args) {
		FindKClosestElements findKClosestElements = new FindKClosestElements();

		int[] input1 = { 10, 15, 7, 3, 4 };
		int num = 8;
		int noOfCloseNumbersToFind = 2;
		findKClosestElements.getKClosestElements(input1, num, noOfCloseNumbersToFind);
		System.out.println();
		
		int[] input2 = {100, 80, 10, 5, 70};
		num = 2;
		noOfCloseNumbersToFind = 3;
		findKClosestElements.getKClosestElements(input2, num, noOfCloseNumbersToFind);
		System.out.println();

		int[] input3 = { 20, 40, 5, 100, 150 };
		num = 100;
		noOfCloseNumbersToFind = 3;
		findKClosestElements.getKClosestElements(input3, num, noOfCloseNumbersToFind);

	}

}

package com.sharat.datastructures.heap;

import java.util.PriorityQueue;

public class BuyMaximumItemsWithAGivenSum {

	public int getMaxBuyableItemsCount(int[] items, int totalAmount) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int item : items) {
			pq.add(item);
		}

		int totalItems = 0;
		while (!pq.isEmpty()) {
			totalAmount = totalAmount - pq.poll();
			if (totalAmount >= 0) {
				totalItems++;
			} else {
				break;
			}
		}
		return totalItems;
	}

	public static void main(String[] args) {
		BuyMaximumItemsWithAGivenSum buyMaximumItemsWithAGivenSum = new BuyMaximumItemsWithAGivenSum();

		int[] items1 = { 1, 12, 5, 13, 200 };
		int sum = 10;
		System.out.println(buyMaximumItemsWithAGivenSum.getMaxBuyableItemsCount(items1, sum));

		int[] items2 = { 20, 10, 30, 100, 5 };
		sum = 35;
		System.out.println(buyMaximumItemsWithAGivenSum.getMaxBuyableItemsCount(items2, sum));

	}

}

package com.sharat.datastructures.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class FractionalKnapsackProblem {

	public int findMaximumValueForAGivenWeight(List<Item> items, int capacity) {
		if (CollectionUtils.isEmpty(items) || capacity <= 0) {
			return 0;
		}
		Collections.sort(items);

		int maxValue = 0;
		for (Item item : items) {
			if (capacity > item.weight) {
				capacity = capacity - item.weight;
				maxValue += item.value;
			} else {
				maxValue += (capacity * item.value) / item.weight;
				capacity = 0;
				break;
			}
		}
		return maxValue;
	}

	private Item prepareItem(int weight, int value) {
		return new Item(weight, value);
	}

	public static void main(String[] args) {
		FractionalKnapsackProblem fkp = new FractionalKnapsackProblem();
		List<Item> items = new ArrayList<>();
		items.add(fkp.prepareItem(50, 600));
		items.add(fkp.prepareItem(20, 500));
		items.add(fkp.prepareItem(30, 400));
		int capacity = 70;
		int maxValue = fkp.findMaximumValueForAGivenWeight(items, capacity);
		System.out.println("Max value for items: " + items + " and capacity: " + capacity + " is:" + maxValue);

		items = new ArrayList<>();
		items.add(fkp.prepareItem(10, 200));
		items.add(fkp.prepareItem(5, 50));
		items.add(fkp.prepareItem(20, 100));
		capacity = 15;
		maxValue = fkp.findMaximumValueForAGivenWeight(items, capacity);
		System.out.println("Max value for items: " + items + " and capacity: " + capacity + " is:" + maxValue);
	}

	class Item implements Comparable<Item> {
		private int weight, value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		// descending order of value
		@Override
		public int compareTo(Item o) {
			return (int) Math.floor((double) (o.value / o.weight) - (double) (this.value / this.weight));
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{weight=");
			builder.append(weight);
			builder.append(", value=");
			builder.append(value);
			builder.append("}");
			return builder.toString();
		}

	}
}

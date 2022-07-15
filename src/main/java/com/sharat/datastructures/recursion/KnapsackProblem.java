package com.sharat.datastructures.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// a knapsack is a problem where the weights are provided and a total 
// specified weight needs to be figured out using the combination of weights
public class KnapsackProblem {
	
	private List<String> resultsList = new ArrayList<String>();
	
	private LinkedList<Integer> resultList = new LinkedList<Integer>();
	
	private int[] weights;

	public List<String> doKnapsack(int[] weights, int sum) {
		if (null == weights || weights.length == 0) {
			return null;
		}
		this.weights = weights;
		reduceWeight(0, sum);
		int resultSize = resultList.size();
		int[] resultArray = new int[resultSize];
		for (int i = 0; i < resultSize; i++) {
			resultArray[i] = resultList.get(i);
		}
		Collections.sort(resultList);
		return resultsList;
	}
	
	private void reduceWeight(int startIndex, int sum) {
		int size = weights.length;
		int newSum;
		for (int i = startIndex; i < size; i++) {
			int weight = weights[i];
			if (weight < sum) {
				resultList.addFirst(weight);
				newSum = sum - weight;
				reduceWeight(i + 1, newSum);
			} else if (weight == sum) {
				resultList.addFirst(weight);
				resultsList.add(resultList.toString());
				sum += resultList.removeFirst();
			}
		}
		if (resultList.size() > 0) {
			sum += resultList.removeFirst();
		}
	}
	
	public static void main(String []args) {
		KnapsackProblem ksp = new KnapsackProblem();
		int[] weights = new int[] {3, 5, 7, 9, 10};
		int sum = 17;
		List<String> resultsList = ksp.doKnapsack(weights, sum);
		System.out.print("Knapsack result for wights : " + Arrays.toString(weights) + ", sum : " + sum + " is : " + resultsList.toString());
	}
	
}

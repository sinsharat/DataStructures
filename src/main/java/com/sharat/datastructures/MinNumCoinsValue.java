/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures;

/**
 * MinNumCoinsValue.java
 *
 * @author S00742735
 * @since 2020-09-18
 */
public class MinNumCoinsValue {

	// m is size of coins array
	// (number of different coins)
	static int minCoins(int coins[], int totalCoins, int coinsSum) {
		// table[i] will be storing
		// the minimum number of coins
		// required for i value. So
		// table[V] will have result
		int table[] = new int[coinsSum + 1];

		// Base case (If given value V is 0)
		table[0] = 0;

		// Initialize all table values as Infinite
		for (int i = 1; i <= coinsSum; i++) {
			table[i] = Integer.MAX_VALUE;
		}

		// Compute minimum coins required for all
		// values from 1 to coinsSum
		int sub_res = 0;
		for (int i = 1; i <= coinsSum; i++) {
			// Go through all coins smaller than i
			for (int j = 0; j < totalCoins; j++) {
				if (coins[j] <= i) {
					sub_res = table[i - coins[j]];
					if (Integer.MAX_VALUE != sub_res) {
						table[i] = Math.min(table[i], sub_res + 1);
					}

				}
			}
		}
		return table[coinsSum];

	}

	// Driver program
	public static void main(String[] args) {
		int coins[] = { 4, 1, 2, 6 };
		int totalCoins = coins.length;
		int coinsSum = 7;
		System.out.println("Minimum coins required is " + minCoins(coins, totalCoins, coinsSum));
	}

}

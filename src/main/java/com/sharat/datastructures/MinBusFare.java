/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures;

/**
 * MinBusFareFinal.java
 *
 * @author S00742735
 * @since 2020-09-15
 */
public class MinBusFare {

	public int minTravelCost(int[] travelDays, int[] ticketTypes, int ticketFares[], int totalDays) {
		int minCost = 0;
		if (null == travelDays || null == ticketTypes || null == ticketFares) {
			return minCost;
		}

		int travelDaysCount = travelDays.length;
		int ticketTypesCount = ticketTypes.length;
		int ticketFaresCount = ticketFares.length;

		if ((totalDays < travelDays[travelDaysCount - 1]) || ticketTypesCount != ticketFaresCount) {
			return 0;
		}

		boolean[] istravelledDaysData = new boolean[totalDays + 1];

		for (int i = 0; i < travelDaysCount; i++) {
			istravelledDaysData[travelDays[i]] = true;
		}

		int[] cumulativeFare = new int[totalDays + 1];
		cumulativeFare[0] = 0;

		int tempMinimalCost = 0;
		int firstIndexExceedSumCounter = 0;
		for (int i = 1; i <= totalDays; i++) {
			if (!istravelledDaysData[i]) {
				cumulativeFare[i] = cumulativeFare[i - 1];
			} else {

				if (ticketTypes[0] == 1 || (i / ticketTypes[0] == firstIndexExceedSumCounter)) {
					tempMinimalCost = cumulativeFare[i - 1] + ticketFares[0];
					firstIndexExceedSumCounter++;
				}

				for (int j = 1; j < ticketFaresCount; j++) {
					if (i - ticketTypes[j] >= 0) {
						tempMinimalCost = Math.min(tempMinimalCost,
								(cumulativeFare[i - ticketTypes[j]] + ticketFares[j]));
					}
				}
				cumulativeFare[i] = tempMinimalCost;
			}
		}

		for (int j = 0; j < ticketTypesCount; j++) {
			if (travelDays[travelDaysCount - 1] < ticketTypes[j]) {
				cumulativeFare[totalDays] = Math.min(cumulativeFare[totalDays], ticketFares[j]);
			}
		}

		minCost = cumulativeFare[totalDays];
		return minCost;
	}

	public static void main(String args[]) {
		int[] travelDays = { 1, 7, 10, 11, 12, 15, 16, 18, 21, 25, 26, 27, 28 };
		int[] tickets = { 1, 7, 30, 60 };
		int[] costs = { 2, 7, 25, 30 };
		MinBusFare minBusFare = new MinBusFare();
		System.out.println(minBusFare.minTravelCost(travelDays, tickets, costs, 31));
	}
}

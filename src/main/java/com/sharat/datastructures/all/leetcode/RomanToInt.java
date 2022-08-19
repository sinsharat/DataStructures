package com.sharat.datastructures.all.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

	public int romanToInt(String s) {
		Map<Character, Integer> romanValMap = new HashMap<>();
		romanValMap.put('I', 1);
		romanValMap.put('V', 5);
		romanValMap.put('X', 10);
		romanValMap.put('L', 50);
		romanValMap.put('C', 100);
		romanValMap.put('D', 500);
		romanValMap.put('M', 1000);

		int total = 0;
		int n = s.length();
		int val;
		char c;
		for (int i = 0; i < n; i++) {
			c = s.charAt(i);
			val = romanValMap.getOrDefault(c, 0);
			if (c == 'I' && i + 1 < n) {
				if (s.charAt(i + 1) == 'V') {
					i++;
					val = 4;
				} else if (s.charAt(i + 1) == 'X') {
					i++;
					val = 9;
				}

			} else if (c == 'X' && i + 1 < n) {
				if (s.charAt(i + 1) == 'L') {
					i++;
					val = 40;
				} else if (s.charAt(i + 1) == 'C') {
					i++;
					val = 90;
				}

			} else if (c == 'C' && i + 1 < n) {
				if (s.charAt(i + 1) == 'D') {
					i++;
					val = 400;
				} else if (s.charAt(i + 1) == 'M') {
					i++;
					val = 900;
				}
			}
			total += val;
		}

		return total;
	}

	public static void main(String[] args) {
		RomanToInt rti = new RomanToInt();
		System.out.println(rti.romanToInt("MCMXCIV")); // 1994
	}

}

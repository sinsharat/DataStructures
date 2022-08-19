package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {
		List<String> resultList = new ArrayList<>();
		if (digits == null || digits.equals("")) {
			return resultList;
		}

		int n = digits.length();
		generateCobinations(digits, n, "", 0, resultList);
		return resultList;
	}

	private void generateCobinations(String digits, int n, String word, int digitIndex, List<String> resultList) {
		if (digitIndex >= n) {
			resultList.add(word);
			return;
		}

		String letters = KEYS[digits.charAt(digitIndex) - '0'];
		for (int i = 0; i < letters.length(); i++) {
			generateCobinations(digits, n, word + letters.charAt(i), digitIndex + 1, resultList);
		}
	}
	
	public static void main(String[] args) {
		LetterCombinationsOfPhoneNumber lc = new LetterCombinationsOfPhoneNumber();
		System.out.println(lc.letterCombinations("2978"));
	}

}

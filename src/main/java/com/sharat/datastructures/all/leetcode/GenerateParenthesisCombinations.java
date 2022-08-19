package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisCombinations {

	public List<String> generateParenthesis(int n) {

		List<String> resultList = new ArrayList<>();

		generateParenthesisCombinations(n, "", 0, 0, resultList);

		return resultList;
	}

	private void generateParenthesisCombinations(int n, String word, int openIndex, int closeIndex,
			List<String> resultList) {

		if (word.length() == n * 2) {
			resultList.add(word);
			return;
		}

		if (openIndex < n) {
			generateParenthesisCombinations(n, word + "(", openIndex + 1, closeIndex, resultList);
		}

		if (closeIndex < openIndex) {
			generateParenthesisCombinations(n, word + ")", openIndex, closeIndex + 1, resultList);
		}
	}

	public static void main(String[] args) {
		GenerateParenthesisCombinations c = new GenerateParenthesisCombinations();
		System.out.println(c.generateParenthesis(8));
	}

}

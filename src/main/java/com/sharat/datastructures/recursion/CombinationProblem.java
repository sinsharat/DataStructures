package com.sharat.datastructures.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A combination problem is where the combination of 
// specified number of letters needs to be found out from the word.
// For example from a 5letter word all 3letter words combinations need to be found.
public class CombinationProblem {
	
	private List<String> combinationsSet = new ArrayList<String>();
	
	private String tempWord;
	
	public List<String> getAllCombinations(String word, int combinationLength) {
		int wordLength;
		if (null == word || (wordLength = word.length()) == 0 || wordLength < combinationLength) {
			return combinationsSet;
		}
		
		for (int i = 0; i <= wordLength - combinationLength; i++) {
			for (int j = 0; j <= wordLength - combinationLength; j++) {
				tempWord = word.substring(j, j + combinationLength);
				prepareCombinations(combinationLength);
			}
			word = word.substring(i+1) + word.substring(0, 1);
		}
		Collections.sort(combinationsSet);
		return combinationsSet;
	}
	
	private void prepareCombinations(int combinationLength) {
		if (combinationLength == 1) {
			return;
		}
		
		int pos;
		for (int i = 0; i < combinationLength; i++) {
			prepareCombinations(combinationLength - 1);
			if (combinationLength == 2 && !combinationsSet.contains(tempWord)) {
				combinationsSet.add(tempWord);
			}
			pos = tempWord.length() - combinationLength;
			tempWord = tempWord.substring(0, pos) + tempWord.substring(pos+1) + tempWord.charAt(pos);
		}
		
	}
	
	public static void main(String []args) {
		CombinationProblem cp = new CombinationProblem();
		String word = "cats";
		int combinationLength = 3;
		List<String> combinationsSet = cp.getAllCombinations(word, combinationLength);
		System.out.println("Combinations of length : " + combinationLength + " for word : " + word + " are :" + combinationsSet.size() + " and the combnations are: ");
		for (String combination : combinationsSet) {
			System.out.println(combination);
		}
	}
}

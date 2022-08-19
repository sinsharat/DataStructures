package com.sharat.datastructures.all.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

	public List<List<String>> groupAnagrams(String[] strs) {	
		Map<String, List<String>> anagramMap = new HashMap<>();
		char[] chars;
		String sortedStr;
		List<String> anagramList;
		for (String str : strs) {
			chars = str.toCharArray();
			Arrays.sort(chars);
			sortedStr = new String(chars);
			anagramList = anagramMap.get(sortedStr);
			if (anagramList == null) {
				anagramList = new ArrayList<>();
				anagramMap.put(sortedStr, anagramList);
			}
			anagramList.add(str);
		}

		List<List<String>> result = new ArrayList<>();
		result.addAll(anagramMap.values());
		return result;
	}
	
	public static void main (String[] args) {
		Anagrams a = new Anagrams();
		System.out.println(a.groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
	}

}

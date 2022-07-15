package com.sharat.datastructures.recursion;

import java.util.LinkedHashSet;
import java.util.Set;

import java.util.LinkedList;

public class Anagrams {

	private Set<String> anagramSet = new LinkedHashSet<String>();
	private LinkedList<Character> charList = new LinkedList<Character>();
	private int wordSize;
	

	public Set<String> getAnagram(String word) {
		wordSize = word.length();
		for (char a : word.toCharArray()) {
			charList.add(a);
		}
		doAnagram(wordSize);
		return anagramSet;
	}
	
	private void doAnagram(int size) {
		if (size == 1) {
			return;
		}
		for (int i = 0; i < size; i++) {
			doAnagram(size - 1);
			if (size == 2) {
				addWord();
			}
			rotate(size);
		}
	}
	
	private void addWord() {
		StringBuilder sb = new StringBuilder();
		for (char a : charList) {
			sb.append(a);
		}
		anagramSet.add(sb.toString());
	}

	private void rotate(int size) {
		if (charList.size() > 1) {
			charList.addLast(charList.remove(wordSize - size));
		}
	}
	
	public static void main(String []args) {
		String anagramWord = "boxes";
		Anagrams anagrams = new Anagrams();
		Set<String> anagramSet = anagrams.getAnagram(anagramWord);
		System.out.println("The word : " + anagramWord + " has : " + anagramSet.size() + " anagrams.");
		for (String anagram : anagramSet) {
			System.out.println(anagram);
		}
	}
}

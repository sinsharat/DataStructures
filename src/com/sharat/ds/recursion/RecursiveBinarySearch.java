package com.sharat.ds.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RecursiveBinarySearch<T extends Comparable<T>> {
	
	Object[] sortedArray;
	
	public RecursiveBinarySearch(List<T> populateData) {
		Set<T> sortedSet = new TreeSet<T>();
		if (null == populateData) {
			throw new IllegalArgumentException("Not a valid list.");
		}
		
		for (T t : populateData) {
			sortedSet.add(t);
		}
		sortedArray = new Object[sortedSet.size()];
		sortedArray = sortedSet.toArray(sortedArray);
	}
	
	public int Search(T t) {
		int startIndex = 0;
		int endIndex = sortedArray.length - 1;
		return find(t, startIndex, endIndex);
	}
	
	@SuppressWarnings("unchecked")
	private int find(T t, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}
		
		int mid = (startIndex + endIndex)/2;
		
		T val = (T)sortedArray[mid];
		
		if (val.equals(t)) {
			return mid;
		} else if (val.compareTo(t) > 1){
			return find(t, startIndex, mid -1);
		} else {
			return find(t, mid + 1, endIndex);
		}
	}
	
	public static void main(String []args) {
		List<String> inputString = new ArrayList<String>();
		inputString.add("a");
		inputString.add("b");
		inputString.add("c");
		inputString.add("d");
		inputString.add("e");
		inputString.add("f");
		inputString.add("g");
		inputString.add("h");
		
		String searchKey= "z";
		
		RecursiveBinarySearch<String> recursivebtSearch = new RecursiveBinarySearch<String>(inputString);
		
		int index = recursivebtSearch.Search(searchKey);
		if (-1 == index) {
			System.out.println("Searched key : " + searchKey + " not found.");
		} else {
			System.out.println("Searched key : " + searchKey + " found at index : " + index + ".");
		}
		
	}

}

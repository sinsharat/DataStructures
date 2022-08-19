package com.sharat.datastructures.all.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {

	public boolean isValid(String s) {

		int n = s.length();
		if (n <= 1) {
			return false;
		}

		if (s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']') {
			return false;
		}

		Map<Character, Character> parenthisMap = new HashMap<>();
		parenthisMap.put('(', ')');
		parenthisMap.put('{', '}');
		parenthisMap.put('[', ']');

		Stack<Character> stack = new Stack<>();
		stack.push(s.charAt(0));
		for (int i = 1; i < n; i++) {
			if (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
				if (stack.size() > 0 && parenthisMap.get(stack.peek()) == s.charAt(i)) {
					stack.pop();
				} else {
					return false;
				}
			} else {
				stack.push(s.charAt(i));
			}
		}

		return stack.isEmpty();
	}

	public static void main(String []args) {
		ValidParenthesis vp = new ValidParenthesis();
		System.out.println(vp.isValid("({[})]"));
		System.out.println(vp.isValid("({[]()})"));
	}
}

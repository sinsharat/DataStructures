package com.sharat.datastructures.backtracking;

public class PermutationWithoutASpecifcCombination {

	public void findAllPermutationsExceptContainingASpecificString(String word, String exceptionStr) {
		if (word.length() <= exceptionStr.length()) {
			return;
		}

		findAllPermutationWithException(word, 0, word.length() - 1, exceptionStr);
	}

	private void findAllPermutationWithException(String word, int l, int r, String exceptionStr) {
		if (l == r) {
			System.out.println(word);
			return;
		} else {
			for (int i = l; i <= r; i++) {
				if (isSafe(word, l, i, r, exceptionStr)) {
					word = new String(swap(word, i, l));
					findAllPermutationWithException(word, l + 1, r, exceptionStr);
					word = new String(swap(word, i, l));
				}
			}
		}
	}

	public static boolean isSafe(String str, int l, int i, int r, String exceptionStr) {
		if (l != 0 && str.charAt(l - 1) == exceptionStr.charAt(0) && str.charAt(i) == exceptionStr.charAt(1)) {
			return false;
		}
		if (r == (l + 1) && str.charAt(i) == exceptionStr.charAt(0) && str.charAt(l) == exceptionStr.charAt(1)) {
			return false;
		}
		return true;
	}

	public static char[] swap(String str, int i, int j) {
		char ch[] = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return ch;
	}

	public static void main(String[] args) {
		PermutationWithoutASpecifcCombination pwsc = new PermutationWithoutASpecifcCombination();
		pwsc.findAllPermutationsExceptContainingASpecificString("ABCDEF", "AB");
	}

}

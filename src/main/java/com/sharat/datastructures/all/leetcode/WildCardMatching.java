package com.sharat.datastructures.all.leetcode;

public class WildCardMatching {

	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();

		// empty p can only match with
		// empty string
		if (n == 0) {
			return m == 0;
		}

		// lookup table for storing results of
		// subproblems
		boolean[][] dp = new boolean[m + 1][n + 1];

		// empty p can match with empty string
		dp[0][0] = true;

		// Only '*' can match with empty string
		for (int j = 1; j <= n; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = true;
			} else {
				break;
			}
		}

		// fill the table in bottom-up fashion
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// Two cases if we see a '*'
				// a) We ignore '*'' character and move
				// to next character in the p,
				// i.e., '*' indicates an empty
				// sequence.
				// b) '*' character matches with ith
				// character in input
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
				} else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
					// Current characters are considered as
					// matching in two cases
					// (a) current character of p is '?'
					// (b) characters actually match
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// If characters don't match
					dp[i][j] = false;
				}
			}
		}

		return dp[m][n];
	}

	public static void main(String[] args) {
		WildCardMatching wcm = new WildCardMatching();
		String str = "baaabab";
		String pattern = "*****ba*****ab";
		// String pattern = "ba*****ab";
		// String pattern = "ba*ab";
		// String pattern = "a*ab";
		// String pattern = "a*****ab";
		// String pattern = "*a*****ab";
		// String pattern = "ba*ab****";
		// String pattern = "****";
		// String pattern = "*";
		// String pattern = "aa?ab";
		// String pattern = "b*b";
		// String pattern = "a*a";
		// String pattern = "baaabab";
		// String pattern = "?baaabab";
		// String pattern = "*baaaba*";

		if (wcm.isMatch(str, pattern)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}

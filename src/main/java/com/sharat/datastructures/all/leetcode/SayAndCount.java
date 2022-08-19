package com.sharat.datastructures.all.leetcode;

/*
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 * 
 * The following are the terms from n=1 to n=10 of the count-and-say sequence:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221 
 * 6.     312211
 * 7.     13112221
 * 8.     1113213211
 * 9.     31131211131221
 * 10.    13211311123113112211
 */
public class SayAndCount {

	public String countAndSay(int n) {
		String s = "1";
		int curNo = -1;
		int prevNo;
		int curNoCount;
		StringBuilder sb;
		for (int i = 2; i <= n; i++) {
			prevNo = -1;
			curNoCount = 0;
			sb = new StringBuilder();
			for (int j = 0; j < s.length(); j++) {
				curNo = s.charAt(j) - '0';
				if (prevNo != -1 && curNo != prevNo) {
					sb.append(curNoCount).append(prevNo);
					curNoCount = 1;
				} else {
					curNoCount++;
				}
				prevNo = curNo;
			}
			sb.append(curNoCount).append(curNo);
			s = sb.toString();
		}

		return s;
	}

	public static void main(String[] args) {
		SayAndCount sc = new SayAndCount();
		System.out.println(sc.countAndSay(7)); // 13112221
	}

}

package com.sharat.datastructures.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	class NumberInfo implements Comparable<NumberInfo> {
		int val, pos, arrNo;

		public NumberInfo(int val, int pos, int arrNo) {
			super();
			this.val = val;
			this.pos = pos;
			this.arrNo = arrNo;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		public int getArrNo() {
			return arrNo;
		}

		public void setArrNo(int arrNo) {
			this.arrNo = arrNo;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("NumberInfo [val=");
			builder.append(val);
			builder.append(", pos=");
			builder.append(pos);
			builder.append(", arrNo=");
			builder.append(arrNo);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(NumberInfo o) {
			return this.val - o.val;
		}

	}

	public Integer[] doMerge(int[][] sortedArrays) {
		List<Integer> result = new ArrayList<Integer>();
		PriorityQueue<NumberInfo> pq = new PriorityQueue<>();
		for (int i = 0; i < sortedArrays.length; i++) {
			pq.add(new NumberInfo(sortedArrays[i][0], 0, i));
		}

		NumberInfo sortedNumInfo;
		int arrNo;
		int pos;
		while (!pq.isEmpty()) {
			sortedNumInfo = pq.poll();
			arrNo = sortedNumInfo.getArrNo();
			pos = sortedNumInfo.getPos();
			result.add(sortedNumInfo.getVal());
			if (sortedArrays[arrNo].length - 1 > sortedNumInfo.getPos()) {
				pq.add(new NumberInfo(sortedArrays[arrNo][pos + 1], pos + 1, arrNo));
			}
		}

		return result.toArray(new Integer[result.size()]);
	}

	public static void main(String[] args) {
		MergeKSortedArrays mergeKSortedArrays = new MergeKSortedArrays();
		int[][] sortedArrays = { { 10, 20, 30 }, { 5, 15 }, { 1, 9, 11, 18 } };
		Integer[] mergedSortedArray = mergeKSortedArrays.doMerge(sortedArrays);
		for (int sortedNum : mergedSortedArray) {
			System.out.print(sortedNum + " ");
		}
	}
}

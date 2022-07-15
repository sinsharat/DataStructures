package com.sharat.datastructures.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfAStream {

	public void findMedians(int[] stream) {
		PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
		int totalQueueSize = 0;
		float median;
		for (int streamNum: stream) {
			totalQueueSize++;
			if (totalQueueSize % 2 != 0) {
				if (leftQueue.peek() != null && leftQueue.peek() < streamNum) {
					rightQueue.add(streamNum);
					streamNum = rightQueue.poll();
				}
				leftQueue.add(streamNum);
				System.out.print(new Float(leftQueue.peek()) + " ");
			} else {
				if (leftQueue.peek() > streamNum) {
					leftQueue.add(streamNum);
					streamNum = leftQueue.poll();
				}
				rightQueue.add(streamNum);
				median = new Float(leftQueue.peek() + rightQueue.peek())/2;
				System.out.print(median + " ");
			}
		}

	}
	
	
	public static void main(String[] args) {
		int[] stream = {12, 15, 10, 5, 8, 7, 16};
		// 
		MedianOfAStream medianOfAStream = new MedianOfAStream();
		System.out.println("Median of streams:");
		medianOfAStream.findMedians(stream);
	}

}

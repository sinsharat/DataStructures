package com.sharat.datastructures.stacknqueue;

import java.util.Stack;

public class Queue<T extends Comparable<T>> {
	
	Stack<T> actualQueue;
	Stack<T> temp;
	long queueSize = Long.MAX_VALUE;
	boolean isPriorityQueue = false;
	
	public Queue() {
		actualQueue = new Stack<T>();
	}
	
	public Queue(boolean isPriorityQueue) {
		actualQueue = new Stack<T>();
		this.isPriorityQueue = isPriorityQueue;
	}

	public Queue(long queueSize) {
		actualQueue = new Stack<T>();
		this.queueSize = queueSize;
	}
	
	public Queue(long queueSize, boolean isPriorityQueue) {
		actualQueue = new Stack<T>();
		this.queueSize = queueSize;
		this.isPriorityQueue = isPriorityQueue;
	}

	public void enqueue(T item) {
		if (isEmpty()) {
			actualQueue.push(item);
		} else {
			if (isPriorityQueue) {
				priorityEnqueue(item);
				return;
			}
			normalEnqueue(item);
		}
	}
	
	private void normalEnqueue(T item) {
		temp = new Stack<T>();
		temp.push(item);
		while (!actualQueue.isEmpty()) {
			temp.push(actualQueue.pop());
		}
		actualQueue.push(item);
		while (!temp.isEmpty()) {
			actualQueue.push(temp.pop());
		}
	}
	
	private void priorityEnqueue(T item) {
		T queueItem;
		T enqueueItem = item;
		temp = new Stack<T>();
		while (!actualQueue.isEmpty()) {
			queueItem = actualQueue.pop();
			if (enqueueItem.compareTo(queueItem) > 0) {
				temp.push(enqueueItem);
				enqueueItem = queueItem;
				continue;
			}
			temp.push(queueItem);
		}
		temp.push(enqueueItem);
		while (!temp.isEmpty()) {
			actualQueue.push(temp.pop());
		}
	}

	public T dequeue() throws Exception {
		if (!isEmpty()) {
			return actualQueue.pop();
		} else {
			throw new Exception("Queue is empty.");
		}

	}

	public T peek() throws Exception {
		if (!isEmpty()) {
			return actualQueue.peek();
		} else {
			throw new Exception("Queue is empty.");
		}
	}

	public boolean isFull() {
		return queueSize <= actualQueue.size();
	}

	public boolean isEmpty() {
		return actualQueue.isEmpty();
	}

	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>(5, true);
		long i;
		System.out.println();
		System.out.println("isQueueEmpty : " + queue.isEmpty());
		System.out.println("isQueueFull : " + queue.isFull());
		while (!queue.isFull()) {
			i = (long) (Math.random() * 100);
			System.out.println("Queueing : " + i);
			queue.enqueue("" + i);
		}
		try {
			while (!queue.isEmpty()) {
				System.out.println("Peek : " + queue.peek());
				System.out.println("Dequeue : " + queue.dequeue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isQueueFull : " + queue.isFull());
		System.out.println("isQueueEmpty : " + queue.isEmpty());
	}
}

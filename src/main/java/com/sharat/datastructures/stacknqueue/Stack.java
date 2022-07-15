package com.sharat.datastructures.stacknqueue;

import java.util.List;
import java.util.LinkedList;

public class Stack<T> {

	private List<T> stack;
	private int lastIndex = -1;
	private int stackSize;

	public Stack(int stackSize) {
		this.stackSize = stackSize;
		stack = new LinkedList<T>();
	}

	public T pop() throws Exception {
		T popVal = null;
		if (!isEmpty()) {
			popVal = stack.get(lastIndex);
			stack.remove(lastIndex);
			lastIndex--;
		} else {
			throw new Exception("Stack is empty.");
		}
		return popVal;
	}

	public void push(T param) throws Exception {
		if (!isFull()) {
			stack.add(param);
			lastIndex++;
		} else {
			throw new Exception("Stack is full.");
		}
	}

	public T peek() throws Exception {
		T popVal = null;
		if (!isEmpty()) {
			popVal = stack.get(lastIndex);
		} else {
			throw new Exception("Stack is empty.");
		}
		return popVal;
	}

	public boolean isFull() {
		boolean isFull = false;
		if (stackSize <= stack.size()) {
			isFull = true;
		}
		return isFull;
	}

	public boolean isEmpty() {
		boolean isEmpty = true;
		if (lastIndex >= 0) {
			return false;
		}
		return isEmpty;
	}

	public static void main(String[] args) {
		try {
			Stack<Integer> stack = new Stack<Integer>(1);
			System.out.println("Before Push --> Is stack empty : " + stack.isEmpty());
			System.out.println("Before Push --> Is stack full : " + stack.isFull());
			stack.push(3);
			System.out.println("After Push --> Is stack empty : " + stack.isEmpty());
			System.out.println("After Push --> Is stack full : " + stack.isFull());
			System.out.println("Peek Val : " + stack.peek());
			System.out.println("After Peek --> Is stack empty : " + stack.isEmpty());
			System.out.println("After Peek --> Is stack full : " + stack.isFull());
			System.out.println("Pop Val : " + stack.pop());
			System.out.println("After pop --> Is stack empty : " + stack.isEmpty());
			System.out.println("After pop --> Is stack full : " + stack.isFull());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

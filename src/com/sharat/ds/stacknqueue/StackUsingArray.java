package com.sharat.ds.stacknqueue;

public class StackUsingArray<T> {

	private T[] stack;
	private int lastIndex = -1;

	public StackUsingArray(T[] stackArray) throws Exception {
		if (null != stackArray && stackArray.length > 0) {
			stack = stackArray;
		} else {
			throw new Exception("Cannot initialize with a null or empty stack");
		}
	}

	public T pop() throws Exception {
		if (!isEmpty()) {
			T popVal = stack[lastIndex];
			stack[lastIndex] = null;
			lastIndex--;
			return popVal;
		} else {
			throw new Exception("Cannot pop more value since stack is empty.");
		}

	}

	public void push(T param) throws Exception 
	{
		if (isFull()) {
			throw new Exception("Cannot push value since stack is full.");
		} else {
			lastIndex++;
			stack[lastIndex] = param;
		}
	}

	public T peek() throws Exception {
		if (!isEmpty()) {
			return stack[lastIndex];
		} else {
			throw new Exception("Cannot peek value since stack is empty.");
		}
	}
	
	public boolean isFull()
	{
		boolean isFull = false;
		if (lastIndex >= stack.length - 1)
		{
			isFull = true;
		}
		return isFull;
	}
	
	public boolean isEmpty()
	{
		boolean isEmpty = true;
		if (lastIndex >= 0)
		{
			return false;
		}
		return isEmpty;
	}
	
	public static void main(String[] args) {
		try {
			StackUsingArray<Integer> stack = new StackUsingArray<Integer>(new Integer[1000]);
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

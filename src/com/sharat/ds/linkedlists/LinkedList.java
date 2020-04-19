package com.sharat.ds.linkedlists;

public class LinkedList<T> {

	class Element {
		
		private T data;
		
		private Element nextData;

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Element getNextData() {
			return nextData;
		}

		public void setNextData(Element nextData) {
			this.nextData = nextData;
		}

	}
	
	public boolean add(T item)
	{
		return false;
	}
	
	public void add(int index, T item)
	{
		
	}
	
	public boolean addAll(LinkedList<T> item)
	{
		return false;
	}
	
	public boolean addAll(int index, LinkedList<T> item)
	{
		return false;
	}
	
	public void addFirst(T item)
	{
		
	}
	
	public void addLasr(T item)
	{
		
	}
	
	public void clear()
	{
		
	}
	
	public int size()
	{
		return 0;
	}
	
}

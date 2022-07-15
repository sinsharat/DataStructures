package com.sharat.datastructures;

public class LULinkedList<T> {

	private Node<T> head;
	
	private Node<T> tail;
	
	private int size;
	
	public LULinkedList() {
		size = 0;
		head = new Node<T>(0, null);
		tail = new Node<T>(0, null);
		head.next = tail;
		tail.previous = head;
	}
	
	public void addToHead(Node<T> node) {
		node.previous = head;
		node.next = head.next;
		head.next.previous = node;
		head.next = node;
		size++;
	}
	
	public void addToTail(Node<T> node) {
		node.next = tail;
		node.previous = tail.previous;
		tail.previous.next = node;
		tail.previous = node;
		size++;
	}
	
	public Node<T> removeNode(Node<T> node) {
		node.previous.next = node.next;
		node.next.previous = node.previous;
		size--;
		return node;
	}
	
	public Node<T> removeFromHead() {
		Node<T> node = head.next;
		return removeNode(node);
	}
	
	public Node<T> removeFromTail() {
		Node<T> node = tail.previous;
		return removeNode(node);
	}
	
	public int size() {
		return size;
	}
	
	public static class Node<T> {
		private int key;
		
		private T value;
		
		private long frequency;
		
		private Node<T> previous;
		
		private Node<T> next;
		
		public Node(int key, T value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
		
		public long getFrequency() {
			return frequency;
		}

		public void setFrequency(long frequency) {
			this.frequency = frequency;
		}

		public Node<T> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + key;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Node<T> other = (Node<T>) obj;
			if (key != other.key)
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> node = head.next;
		
		for (int i = 0; node != null && i < size; i++, node = node.next) {
			if (i != 0) {
				sb.append(",");	
			}
			sb.append(node.getValue());
		}
		return sb.toString();
	}
}

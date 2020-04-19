package com.sharat.ds.interview;

import java.util.HashMap;
import java.util.Map;

import com.sharat.ds.interview.LULinkedList.Node;

public class LRUCache {

	private Map<Integer, Node<Integer>> cache = null;

	private LULinkedList<Integer> lruCache = null;

	private int totalCapacity = 0;

	public LRUCache(int totalCapacity) {
		this.totalCapacity = totalCapacity;
		cache = new HashMap<Integer, Node<Integer>>();
		lruCache = new LULinkedList<Integer>();
	}

	public void addToCache(Integer key, Integer value) {
		Node<Integer> node = null;
		
		if (cache.containsKey(key)) {
			node = cache.get(key);
			lruCache.removeNode(node);
			cache.remove(key);
		} else if (lruCache.size() >= totalCapacity) {
			// remove oldest entry
			Node<Integer> removedNode = lruCache.removeFromTail();
			cache.remove(removedNode.getKey());
		}

		node = new Node<Integer>(key, value);
		lruCache.addToHead(node);
		cache.put(key, node);
	}

	public void display() {
		System.out.println("LRUCache : [" + lruCache.toString() + "]");
	}
	
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(5);
		lruCache.addToCache(1, 1);
		lruCache.addToCache(2, 2);
		lruCache.addToCache(3, 3);
		lruCache.addToCache(4, 4);
		lruCache.addToCache(5, 5);
		lruCache.addToCache(3, 3);
		lruCache.addToCache(6, 6);
		lruCache.addToCache(7, 7);
		lruCache.addToCache(1, 1);
		lruCache.display();
	}
}

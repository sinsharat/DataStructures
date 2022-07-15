package com.sharat.datastructures;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.sharat.datastructures.LULinkedList.Node;

public class LRULFUCache {
	
	private static final int LRU_TYPE = 0;
	
	private static final int LFU_TYPE = 1;
	
	private Map<Integer, Node<Integer>> cacheMap = null;

	private TreeMap<Long, LULinkedList<Integer>> lfuCacheMap = null;
	
	private int totalCapacity = 0;
	
	private int lfuSize = 0;
	
	private int cacheType = LRU_TYPE;
	
	public LRULFUCache(int totalCapacity, int cacheType) {
		this.totalCapacity = totalCapacity;
		lfuSize = 0;
		cacheMap = new HashMap<Integer, Node<Integer>>(totalCapacity);
		lfuCacheMap = new TreeMap<Long, LULinkedList<Integer>>();
		if (cacheType == LFU_TYPE) {
			this.cacheType = cacheType;
		}
	}
	
	public void addToCache(Integer key, Integer value) {
		Node<Integer> node = null;
		LULinkedList<Integer> frequencyLinkedList = null;
		long frequency = 0;
		long newFrequency = cacheType == LFU_TYPE ? 1 : new Date().getTime();
		if (cacheMap.containsKey(key)) {
			node = cacheMap.get(key);
			frequency = node.getFrequency();
			newFrequency = cacheType == LFU_TYPE ? frequency + 1 : new Date().getTime();
			frequencyLinkedList = lfuCacheMap.get(frequency);
			frequencyLinkedList.removeNode(node);
			lfuSize--;
			if (frequencyLinkedList.size() == 0) {
				lfuCacheMap.remove(frequency);
			}
			cacheMap.remove(key);
		} else if (lfuSize >= totalCapacity) {
			Entry<Long, LULinkedList<Integer>> keyFrequencyLinkedListEntry = lfuCacheMap.firstEntry();
			frequency = keyFrequencyLinkedListEntry.getKey();
			frequencyLinkedList = keyFrequencyLinkedListEntry.getValue();
			node = frequencyLinkedList.removeFromTail();
			lfuSize--;
			if (frequencyLinkedList.size() == 0) {
				lfuCacheMap.remove(frequency);
			}
			cacheMap.remove(node.getKey());
		}
		
		node = new Node<Integer>(key, value);
		node.setFrequency(newFrequency);
		
		frequencyLinkedList = lfuCacheMap.get(newFrequency);
		if (null == frequencyLinkedList) {
			frequencyLinkedList = new LULinkedList<Integer>();
			lfuCacheMap.put(newFrequency, frequencyLinkedList);
		}
		lfuSize++;
		frequencyLinkedList.addToHead(node);
		cacheMap.put(key, node);
	}
	
	public void display() {
		StringBuilder sb = new StringBuilder();
		if (cacheType == LRU_TYPE) {
			sb.append("LRUCache : [");
		} else {
			sb.append("LFUCache : [");
		}
		
		int count = 0;
		for (Entry<Long, LULinkedList<Integer>> entry : lfuCacheMap.entrySet()) {
			if (count != 0)
			{
				sb.append(",");
			}
			if (cacheType == LRU_TYPE) {
				sb.append(entry.getValue());
			} else {
				sb.append(entry);
			}
			count++;
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public static void main(String []args) {
		LRULFUCache lruCache = new LRULFUCache(5, LRU_TYPE);
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
		
		LRULFUCache lfuCache = new LRULFUCache(5, LFU_TYPE);
		lfuCache.addToCache(1, 1);
		lfuCache.addToCache(2, 2);
		lfuCache.addToCache(3, 3);
		lfuCache.addToCache(4, 4);
		lfuCache.addToCache(5, 5);
		lfuCache.addToCache(3, 3);
		lfuCache.addToCache(6, 6);
		lfuCache.addToCache(7, 7);
		lfuCache.addToCache(1, 1);
		lfuCache.display();
	}
}

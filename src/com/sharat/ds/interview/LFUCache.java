package com.sharat.ds.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.sharat.ds.interview.LULinkedList.Node;

public class LFUCache {
	
	private Map<Integer, Node<Integer>> cacheMap = null;

	private TreeMap<Long, LULinkedList<Integer>> lfuCacheMap = null;
	
	private int totalCapacity = 0;
	
	private int lfuSize = 0;
	
	public LFUCache(int totalCapacity) {
		this.totalCapacity = totalCapacity;
		lfuSize = 0;
		cacheMap = new HashMap<Integer, Node<Integer>>(totalCapacity);
		lfuCacheMap = new TreeMap<Long, LULinkedList<Integer>>();
	}
	
	public void addToCache(Integer key, Integer value) {
		Node<Integer> node = null;
		LULinkedList<Integer> frequencyLinkedList = null;
		long frequency = 0;
		long newFrequency = 1;
		if (cacheMap.containsKey(key)) {
			node = cacheMap.get(key);
			frequency = node.getFrequency();
			newFrequency = frequency + 1;
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
		sb.append("LFUCache : [");
		int count = 0;
		for (Entry<Long, LULinkedList<Integer>> entry : lfuCacheMap.entrySet()) {
			if (count != 0)
			{
				sb.append(",");
			}
			sb.append(entry);
			count++;
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public static void main(String []args) {
		LFUCache lfuCache = new LFUCache(5);
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

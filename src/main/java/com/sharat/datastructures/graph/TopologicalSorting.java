package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.collections4.CollectionUtils;

public class TopologicalSorting {

	public void doTopologicalSortingUsingBFSKahnsAlgorithm(ArrayList<ArrayList<Integer>> topologicalGraph) {
		if (CollectionUtils.isEmpty(topologicalGraph)) {
			return;
		}
		
		int totalVertices = topologicalGraph.size();
		int[] topographicalOrder = new int[totalVertices];
		
		// define execution order
		for (ArrayList<Integer> adjecentList: topologicalGraph) {
			if (CollectionUtils.isEmpty(adjecentList)) {
				continue;
			}
			
			for (Integer adjecentVertex: adjecentList) {
				if (adjecentVertex != null) {
					topographicalOrder[adjecentVertex]++;
				}
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < totalVertices; i++) {
			if (topographicalOrder[i] == 0) {
				queue.add(i);
			}
		}
		
		int parentVertex;
		List<Integer> adjecencyList;
		while (!queue.isEmpty()) {
			parentVertex = queue.poll();
			System.out.print(parentVertex + " ");
			adjecencyList = topologicalGraph.get(parentVertex);
			if (!CollectionUtils.isEmpty(adjecencyList)) {
				for (int currentVertex: adjecencyList) {
					topographicalOrder[currentVertex]--;
					if (topographicalOrder[currentVertex] == 0) {
						queue.add(currentVertex);
					}
				}
			}
		}
	}
	
	public void doTopologicalSortingUsingDFS(ArrayList<ArrayList<Integer>> topologicalGraph) {
		if (CollectionUtils.isEmpty(topologicalGraph)) {
			return;
		}
		
		int totalVertices = topologicalGraph.size();
		Stack<Integer> order = new Stack<>();
		boolean[] isVisited = new boolean[totalVertices];
		
		for (int i = 0; i < totalVertices; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				doDFSTraversal(topologicalGraph, isVisited, i, order);
				order.push(i);
			}
		}
		
		while(!order.isEmpty()) {
			System.out.print(order.pop() + " ");
		}
	}
	
	private void doDFSTraversal(ArrayList<ArrayList<Integer>> topologicalGraph, boolean[] isVisited, int parent,
			Stack<Integer> order) {
		List<Integer> adjecentList = topologicalGraph.get(parent);
		
		if (!CollectionUtils.isEmpty(adjecentList)) {
			for (int currentVertex: adjecentList) {
				if (!isVisited[currentVertex]) {
					isVisited[currentVertex] = true;
					doDFSTraversal(topologicalGraph, isVisited, currentVertex, order);
					order.push(currentVertex);
				}
			}
		}
	}

	public static void main(String[] args) {
		TopologicalSorting topologicalSorting = new TopologicalSorting();

		/**
		 * 0---->1 
		 * |     |  
		 * |     |    
		 * |     |     
		 * |     |      
		 * v     V       
		 * 2---->3----->4
		 */
		ArrayList<ArrayList<Integer>> topologicalGraph = new ArrayList<>();
		topologicalGraph.add(0, new ArrayList<Integer>());
		topologicalGraph.add(1, new ArrayList<Integer>());
		topologicalGraph.add(2, new ArrayList<Integer>());
		topologicalGraph.add(3, new ArrayList<Integer>());
		topologicalGraph.add(4, new ArrayList<Integer>());
		topologicalGraph.get(0).add(1);
		topologicalGraph.get(0).add(2);
		topologicalGraph.get(2).add(3);
		topologicalGraph.get(1).add(3);
		topologicalGraph.get(3).add(4);
		System.out.println("doTopologicalSortingUsingBFSKahnsAlgorithm: ");
		topologicalSorting.doTopologicalSortingUsingBFSKahnsAlgorithm(topologicalGraph);
		System.out.println("\ndoTopologicalSortingUsingDFS: ");
		topologicalSorting.doTopologicalSortingUsingDFS(topologicalGraph);
		/**
		 * 
		 * 4             1
		 * |\           /|
		 * | \         / |
		 * |  \       /  |
		 * |   \     /   |
		 * v    \> </    v    
		 * 2----->0----->3
		 *
		 */
		System.out.println();
		topologicalGraph = new ArrayList<>();
		topologicalGraph.add(0, new ArrayList<Integer>());
		topologicalGraph.add(1, new ArrayList<Integer>());
		topologicalGraph.add(2, new ArrayList<Integer>());
		topologicalGraph.add(3, new ArrayList<Integer>());
		topologicalGraph.add(4, new ArrayList<Integer>());
		topologicalGraph.get(4).add(2);
		topologicalGraph.get(4).add(0);
		topologicalGraph.get(2).add(0);
		topologicalGraph.get(0).add(3);
		topologicalGraph.get(1).add(3);
		topologicalGraph.get(1).add(0);
		System.out.println("doTopologicalSortingUsingBFSKahnsAlgorithm: ");
		topologicalSorting.doTopologicalSortingUsingBFSKahnsAlgorithm(topologicalGraph);
		System.out.println("\ndoTopologicalSortingUsingDFS: ");
		topologicalSorting.doTopologicalSortingUsingDFS(topologicalGraph);
	}
	
}

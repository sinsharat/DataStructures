package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections4.CollectionUtils;


public class DetectCycleInADirectedGraph {
	
	public boolean checkDirectedCyclicGraphUsingDFS(ArrayList<ArrayList<Integer>> inputGraph) {
		if (CollectionUtils.isEmpty(inputGraph)) {
			return false;
		}
		
		int totalVertices = inputGraph.size();
		boolean[] isVisited = new boolean[totalVertices];
		for (int i = 0; i < totalVertices; i++) {
			if (!isVisited[i]) {
				boolean[] recursionCallStack = new boolean[totalVertices];
				recursionCallStack[i] = true;
				isVisited[i] = true;
				if (checkPathContainsCycle(inputGraph, i, recursionCallStack, isVisited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	private boolean checkPathContainsCycle(ArrayList<ArrayList<Integer>> inputGraph, int current, boolean[] recursionCallStack, boolean[] isVisited) {
		List<Integer> adejectVertices = inputGraph.get(current);
		for (int adjectentVertex : adejectVertices) {
			if (!recursionCallStack[adjectentVertex]) {
				isVisited[adjectentVertex] = true;
				recursionCallStack[adjectentVertex] = true;
				if (checkPathContainsCycle(inputGraph, adjectentVertex, recursionCallStack, isVisited)) {
					return true;
				}
			} else {
				return true;
			}
			recursionCallStack = new boolean[inputGraph.size()];
		}
		
		return false;
	}
	
	public boolean checkDirectedCyclicGraphUsingBFSKahnsAlgorithm(ArrayList<ArrayList<Integer>> inputGraph) {
		if (CollectionUtils.isEmpty(inputGraph)) {
			return false;
		}
		
		int totalVertices = inputGraph.size();
		int[] vertexDependencies = new int[totalVertices];
		
		// define execution order
		for (ArrayList<Integer> adjecentList: inputGraph) {
			if (CollectionUtils.isEmpty(adjecentList)) {
				continue;
			}
			
			for (Integer adjecentVertex: adjecentList) {
				if (adjecentVertex != null) {
					vertexDependencies[adjecentVertex]++;
				}
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < totalVertices; i++) {
			if (vertexDependencies[i] == 0) {
				queue.add(i);
			}
		}
		
		int vertexCount = 0;
		int parentVertex;
		List<Integer> adjecencyList;
		while (!queue.isEmpty()) {
			parentVertex = queue.poll();
			vertexCount++;
			adjecencyList = inputGraph.get(parentVertex);
			if (!CollectionUtils.isEmpty(adjecencyList)) {
				for (int currentVertex: adjecencyList) {
					vertexDependencies[currentVertex]--;
					if (vertexDependencies[currentVertex] == 0) {
						queue.add(currentVertex);
					}
				}
			}
		}
		
		return vertexCount != totalVertices;
	}


	public static void main(String[] args) {
		DetectCycleInADirectedGraph detectCycleInADirectedGraph = new DetectCycleInADirectedGraph();

		/**
		 * 0---->1< 
		 * |     | \ 
		 * |     |  \  
		 * |     |   \  
		 * |     |    \  
		 * v     V     \  
		 * 2---->3----->4
		 */
		ArrayList<ArrayList<Integer>> directedCyclicGraph = new ArrayList<>();
		directedCyclicGraph.add(0, new ArrayList<Integer>());
		directedCyclicGraph.add(1, new ArrayList<Integer>());
		directedCyclicGraph.add(2, new ArrayList<Integer>());
		directedCyclicGraph.add(3, new ArrayList<Integer>());
		directedCyclicGraph.add(4, new ArrayList<Integer>());
		directedCyclicGraph.get(0).add(1);
		directedCyclicGraph.get(0).add(2);
		directedCyclicGraph.get(2).add(3);
		directedCyclicGraph.get(1).add(3);
		directedCyclicGraph.get(3).add(4);
		directedCyclicGraph.get(4).add(1);
		boolean result = detectCycleInADirectedGraph.checkDirectedCyclicGraphUsingDFS(directedCyclicGraph);
		System.out.println("directedCyclicGraphUsingDFS: " + result);
		result = detectCycleInADirectedGraph.checkDirectedCyclicGraphUsingBFSKahnsAlgorithm(directedCyclicGraph);
		System.out.println("directedCyclicGraphUsingBFS: " + result);
		/**
		 * 
		 * 0---->1 
		 * |     |  
		 * |     |    
		 * |     |    
		 * |     |    
		 * v     V       
		 * 2---->3----->4
		 *
		 */
		ArrayList<ArrayList<Integer>> directedAcyclicGraph = new ArrayList<>();
		directedAcyclicGraph.add(0, new ArrayList<Integer>());
		directedAcyclicGraph.add(1, new ArrayList<Integer>());
		directedAcyclicGraph.add(2, new ArrayList<Integer>());
		directedAcyclicGraph.add(3, new ArrayList<Integer>());
		directedAcyclicGraph.add(4, new ArrayList<Integer>());
		directedAcyclicGraph.get(0).add(1);
		directedAcyclicGraph.get(0).add(2);
		directedAcyclicGraph.get(1).add(3);
		directedAcyclicGraph.get(2).add(3);
		directedAcyclicGraph.get(3).add(4);
		result = detectCycleInADirectedGraph.checkDirectedCyclicGraphUsingDFS(directedAcyclicGraph);
		System.out.println("directedAcyclicGraphUsingDFS: " + result);
		result = detectCycleInADirectedGraph.checkDirectedCyclicGraphUsingBFSKahnsAlgorithm(directedAcyclicGraph);
		System.out.println("directedAcyclicGraphUsingBFS: " + result);
	}

}

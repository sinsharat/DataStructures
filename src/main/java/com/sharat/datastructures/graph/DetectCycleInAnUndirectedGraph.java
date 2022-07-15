package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections4.CollectionUtils;

public class DetectCycleInAnUndirectedGraph {

	public boolean checkCycleInGraphUsingDFS(ArrayList<ArrayList<Integer>> inputGraph) {
		if (CollectionUtils.isEmpty(inputGraph)) {
			return false;
		}
		
		int totalVertex = inputGraph.size();
		boolean[] isVisited = new boolean[totalVertex];
		boolean result = false;
		for (int i = 0; i < totalVertex; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				result = checkIsCyclic(inputGraph, isVisited, i, -1);
				if (result) {
					return result;
				}
			}
		}
		return result;
	}
	
	private boolean checkIsCyclic(ArrayList<ArrayList<Integer>> inputGraph, boolean[] isVisited, int current, int parent) {
		List<Integer> adjecentList = inputGraph.get(current);
		for (int currentVertex: adjecentList) {
			if (!isVisited[currentVertex]) {
				isVisited[currentVertex] = true;
				if (checkIsCyclic(inputGraph, isVisited, currentVertex, current)) {
					return true;
				}
			} else if (isVisited[currentVertex] && parent != currentVertex) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCycleInGraphUsingBFS(ArrayList<ArrayList<Integer>> inputGraph) {
		if (CollectionUtils.isEmpty(inputGraph)) {
			return false;
		}
		
		int totalVertices = inputGraph.size();
		boolean[] isVisited = new boolean[totalVertices];
		int[] parentVertex = new int[totalVertices];
		Queue<Integer> queue = new LinkedList<Integer>();
		List<Integer> adjecentList;
		int currentVertex;
		for (int i = 0; i < totalVertices; i++) {
			if (!isVisited[i]) {
				queue.add(i);
				isVisited[i] = true;
				while (!queue.isEmpty()) {
					currentVertex = queue.poll();
					adjecentList = inputGraph.get(currentVertex);
					
					for (int vertex: adjecentList) {
						if (!isVisited[vertex]) {
							isVisited[vertex] = true;
							parentVertex[vertex] = currentVertex;
							queue.add(vertex);
						} else if (isVisited[vertex] && parentVertex[currentVertex] != vertex) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		DetectCycleInAnUndirectedGraph detectedCycleInAnIndirectedGraph = new DetectCycleInAnUndirectedGraph();

		/**
		 * 0-----1 
		 * |      \ 
		 * |       \ 
		 * |        \ 
		 * |         \ 
		 * |          \ 
		 * 2-----3-----4
		 */
		ArrayList<ArrayList<Integer>> cycleGraph = new ArrayList<>();
		cycleGraph.add(0, new ArrayList<Integer>());
		cycleGraph.add(1, new ArrayList<Integer>());
		cycleGraph.add(2, new ArrayList<Integer>());
		cycleGraph.add(3, new ArrayList<Integer>());
		cycleGraph.add(4, new ArrayList<Integer>());
		cycleGraph.get(0).add(1);
		cycleGraph.get(1).add(0);
		cycleGraph.get(0).add(2);
		cycleGraph.get(2).add(0);
		cycleGraph.get(2).add(3);
		cycleGraph.get(3).add(2);
		cycleGraph.get(3).add(4);
		cycleGraph.get(4).add(3);
		cycleGraph.get(1).add(4);
		cycleGraph.get(4).add(1);
		boolean result = detectedCycleInAnIndirectedGraph.checkCycleInGraphUsingDFS(cycleGraph);
		System.out.println("cyclicGraphUsingDFS: " + result);
		result = detectedCycleInAnIndirectedGraph.checkCycleInGraphUsingBFS(cycleGraph);
		System.out.println("cyclicGraphUsingBFS: " + result);
		/**
		 * 0-----1------2------3 
		 *       |      | 
		 *       |      | 
		 *       4      5
		 */
		ArrayList<ArrayList<Integer>> acyclicGraph = new ArrayList<>();
		acyclicGraph.add(0, new ArrayList<Integer>());
		acyclicGraph.add(1, new ArrayList<Integer>());
		acyclicGraph.add(2, new ArrayList<Integer>());
		acyclicGraph.add(3, new ArrayList<Integer>());
		acyclicGraph.add(4, new ArrayList<Integer>());
		acyclicGraph.add(5, new ArrayList<Integer>());
		acyclicGraph.get(0).add(1);
		acyclicGraph.get(1).add(0);
		acyclicGraph.get(1).add(2);
		acyclicGraph.get(2).add(1);
		acyclicGraph.get(1).add(4);
		acyclicGraph.get(4).add(1);
		acyclicGraph.get(2).add(3);
		acyclicGraph.get(3).add(2);
		acyclicGraph.get(2).add(5);
		acyclicGraph.get(5).add(2);
		result = detectedCycleInAnIndirectedGraph.checkCycleInGraphUsingDFS(acyclicGraph);
		System.out.println("acyclicGraphUsingDFS: " + result);
		result = detectedCycleInAnIndirectedGraph.checkCycleInGraphUsingBFS(acyclicGraph);
		System.out.println("acyclicGraphUsingBFS: " + result);
	}

}

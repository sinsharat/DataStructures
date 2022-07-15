package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections4.CollectionUtils;

/*
 * Uses BFS
 */
public class ShortestPathInUnWeightedUnDirectedGraph {

	/**
	 * getShortestDistanceOfVerticesFromASpecificVertex
	 * 
	 * @param adjacencyList ArrayList<ArrayList<Integer>>
	 * @param startNumber   int
	 */
	public int[] getShortestDistanceOfVerticesFromASpecificVertex(ArrayList<ArrayList<Integer>> adjacencyList,
			int startNumber) {
		if (CollectionUtils.isEmpty(adjacencyList)) {
			return null;
		}
		
		int totalVertices = adjacencyList.size();
		boolean[] isVisited = new boolean[totalVertices];
		int[] shortestDistance = new int[totalVertices];
		Queue<Integer> vertexList = new LinkedList<>();
		vertexList.add(startNumber);
		shortestDistance[startNumber] = 0;
		isVisited[startNumber] = true;
		int parentIndex;
		List<Integer> adjecentVertices;
		while (!vertexList.isEmpty()) {
			parentIndex = vertexList.poll();
			adjecentVertices = adjacencyList.get(parentIndex);
			for (int currentVertex : adjecentVertices) {
				if (!isVisited[currentVertex]) {
					vertexList.add(currentVertex);
					isVisited[currentVertex] = true;
					shortestDistance[currentVertex] = shortestDistance[parentIndex] + 1;
				}
			}
		}
		return shortestDistance;
	}
	
	public static void main(String[] args) {
		/**
		 *                0-----1
		 *                |    / \
		 *                |   /  3
		 *                |  /  /|  
		 *                | / /  | 
 		 *                |//    |
		 *                2------4
		 */               
		ArrayList<ArrayList<Integer>> adjacencyListConnectedGraph = new ArrayList<>();
		adjacencyListConnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListConnectedGraph.get(0).add(1);
		adjacencyListConnectedGraph.get(1).add(0);
		adjacencyListConnectedGraph.get(0).add(2);
		adjacencyListConnectedGraph.get(2).add(0);
		adjacencyListConnectedGraph.get(1).add(2);
		adjacencyListConnectedGraph.get(2).add(1);
		adjacencyListConnectedGraph.get(1).add(3);
		adjacencyListConnectedGraph.get(3).add(1);
		adjacencyListConnectedGraph.get(2).add(3);
		adjacencyListConnectedGraph.get(3).add(2);
		adjacencyListConnectedGraph.get(2).add(4);
		adjacencyListConnectedGraph.get(4).add(2);
		adjacencyListConnectedGraph.get(3).add(4);
		adjacencyListConnectedGraph.get(4).add(3);
		
		ShortestPathInUnWeightedUnDirectedGraph shortestDistanceInUnWeightedGraph = new ShortestPathInUnWeightedUnDirectedGraph();
		int[] shortestDistances = shortestDistanceInUnWeightedGraph.getShortestDistanceOfVerticesFromASpecificVertex(adjacencyListConnectedGraph, 0);
		int size = shortestDistances.length;
		for (int i = 0; i < size; i++) {
			System.out.println(i + ": " + shortestDistances[i]);
		}
	}

}

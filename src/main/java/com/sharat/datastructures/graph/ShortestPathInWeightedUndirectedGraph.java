package com.sharat.datastructures.graph;

import java.util.Arrays;

public class ShortestPathInWeightedUndirectedGraph {

	// Digikstra's Shortest Path Algorithm (Does not work for negative weights but works for both directed and undirected graph which may have cycles as well)
	// Note: Time complexity can be reduced by using adjecencyList with Min heap(Priority Queue) instead of matrix
	public int[] findShortestPathFromAGivenVertex(int[][] graph, int src) {
		if (graph == null || graph.length == 0) {
			return null;
		}
		
		int totalVertices = graph.length;
		int[] shortestDistanceFromSouce = new int[totalVertices];
		Arrays.fill(shortestDistanceFromSouce, Integer.MAX_VALUE);
		
		boolean[] isFinalised = new boolean[totalVertices];
		shortestDistanceFromSouce[src] = 0;
		isFinalised[src] = true;
		int totalVertexFinalisedCount = 1; 
		
		int evalVertex = src;
		int minDistance;
		int totalDistance;
		while (totalVertexFinalisedCount < totalVertices) {
			// update shortest distance
			for (int i = 0; i < totalVertices; i++) {
				totalDistance = shortestDistanceFromSouce[evalVertex] + graph[evalVertex][i];
				if (graph[evalVertex][i] != 0 && shortestDistanceFromSouce[i] > totalDistance) {
					shortestDistanceFromSouce[i] = totalDistance;
				}
			}
			
			// find evalVertex
			minDistance = Integer.MAX_VALUE;
			for (int i = 0; i < totalVertices; i++) {
				if (!isFinalised[i] && minDistance > shortestDistanceFromSouce[i]) {
					minDistance = shortestDistanceFromSouce[i];
					evalVertex = i;
				}
			}
			isFinalised[evalVertex] = true;
			totalVertexFinalisedCount++;
		}
		
		return shortestDistanceFromSouce;
	}
	
	public static void main(String[] args) {
		ShortestPathInWeightedUndirectedGraph spwig = new ShortestPathInWeightedUndirectedGraph();

		/**
 		 *    1     3      6
		 * 0-----1-----2-----3 
		 *  \    |    /     /
		 *  4\  5|  2/     /
		 *    \  |  /    5/
		 *     \ | /     /
		 *      \|/  7  /
		 *       4-----5
		 *       
		 *    0  1  2  3  4  5
		 *  0|0  1  0  0  4  0|
		 *  1|1  0  3  0  5  0|
		 *  2|0  3  0  6  2  0|
		 *  3|0  0  6  0  0  5|
		 *  4|4  5  2  0  0  7|
		 *  5|0  0  0  5  7  0|
		 *       
		 */  
		
		int[][] graph = new int[6][6];
		graph[0][1] = 1;
		graph[1][0] = 1;
		graph[0][4] = 4;
		graph[4][0] = 4;
		graph[1][4] = 5;
		graph[4][1] = 5;
		graph[1][2] = 3;
		graph[2][1] = 3;
		graph[2][4] = 2;
		graph[4][2] = 2;
		graph[2][3] = 6;
		graph[3][2] = 6;
		graph[3][5] = 5;
		graph[5][3] = 5;
		graph[4][5] = 7;
		graph[5][4] = 7;
		int source = 0;
		int[] shortestDistanceFromSouce = spwig.findShortestPathFromAGivenVertex(graph, source);
		int length = shortestDistanceFromSouce.length;
		System.out.println("Shortest distance from source " + source + ": ");
		for (int i = 0; i < length; i++) {
			System.out.println(i + ": " + shortestDistanceFromSouce[i]);
		}
	}

}

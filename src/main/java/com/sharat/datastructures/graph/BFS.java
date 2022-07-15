package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

	public void printAllAdjecentNodesInConnectedGraphFromAPoint(ArrayList<ArrayList<Integer>> adjacencyList) {
		if (adjacencyList == null || adjacencyList.isEmpty()) {
			return;
		}
		
		int totalNoOfVertices = adjacencyList.size();
		List<Integer> adjecentVertexList;
		int parentVertex;
		Queue<Integer> vertexQueue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[totalNoOfVertices];
		
		for (int i = 0; i < totalNoOfVertices; i++) {
			if (!isVisited[i]) {
				vertexQueue.add(i);
				isVisited[i] = true;
				while (!vertexQueue.isEmpty()) {
					parentVertex = vertexQueue.poll();
					System.out.print(parentVertex + " ");
					adjecentVertexList = adjacencyList.get(parentVertex);
					for (int currentVertex: adjecentVertexList) {
						if (!isVisited[currentVertex]) {
							vertexQueue.add(currentVertex);
							isVisited[currentVertex] = true;
						}
					}
				}
			}
		}
		
	}

	public static void main(String[] args) {
		BFS bfs = new BFS();
		
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
		bfs.printAllAdjecentNodesInConnectedGraphFromAPoint(adjacencyListConnectedGraph);

		System.out.println("");
		
		/**
		 *                0-----1  4-----5
		 *                |    /   |    /
		 *                |   /    |   /
		 *                |  3     |  /
		 *                | /      | / 
 		 *                |/       |/
		 *                2        6
		 */               
		ArrayList<ArrayList<Integer>> adjacencyListDisconnectedGraph = new ArrayList<>();
		adjacencyListDisconnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(5, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(6, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.get(0).add(1);
		adjacencyListDisconnectedGraph.get(1).add(0);
		adjacencyListDisconnectedGraph.get(0).add(2);
		adjacencyListDisconnectedGraph.get(2).add(0);
		adjacencyListDisconnectedGraph.get(1).add(3);
		adjacencyListDisconnectedGraph.get(3).add(1);
		adjacencyListDisconnectedGraph.get(2).add(3);
		adjacencyListDisconnectedGraph.get(3).add(2);

		adjacencyListDisconnectedGraph.get(4).add(5);
		adjacencyListDisconnectedGraph.get(5).add(4);
		adjacencyListDisconnectedGraph.get(4).add(6);
		adjacencyListDisconnectedGraph.get(6).add(4);
		adjacencyListDisconnectedGraph.get(6).add(5);
		adjacencyListDisconnectedGraph.get(5).add(6);
		bfs.printAllAdjecentNodesInConnectedGraphFromAPoint(adjacencyListDisconnectedGraph);
	}

}

package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class DFS {

	public void printAllChainNodesInConnectedGraphFromAPoint(ArrayList<ArrayList<Integer>> chainList) {
		
		if (chainList == null || chainList.isEmpty()) {
			return;
		}
		
		int chainListSize = chainList.size();
		boolean[] isVisited = new boolean[chainListSize];
		
		for (int i = 0; i < chainListSize; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				doDFS(chainList, isVisited, i);
			}
		}		
	}
	
	private void doDFS(ArrayList<ArrayList<Integer>> chainList, boolean[] isVisited, int parent) {		
		System.out.print(parent + " ");
		List<Integer> adjecentVertices = chainList.get(parent);
		for (int currentVertex: adjecentVertices) {
			if (!isVisited[currentVertex]) {
				isVisited[currentVertex] = true;
				doDFS(chainList, isVisited, currentVertex);
			}
		}
	}
	
	public static void main(String[] args) {
		DFS dfs = new DFS();
		/**
		 *                     0-------3
		 *                     |       |
		 *                     |       |
		 *                     |       |
		 *                     |       |
		 *               1-----2-------4
		 */
		ArrayList<ArrayList<Integer>> adjacencyListConnectedGraph = new ArrayList<>();
		adjacencyListConnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListConnectedGraph.get(0).add(2);
		adjacencyListConnectedGraph.get(2).add(0);
		adjacencyListConnectedGraph.get(1).add(2);
		adjacencyListConnectedGraph.get(2).add(1);
		adjacencyListConnectedGraph.get(2).add(4);
		adjacencyListConnectedGraph.get(4).add(2);
		adjacencyListConnectedGraph.get(3).add(4);
		adjacencyListConnectedGraph.get(4).add(3);
		dfs.printAllChainNodesInConnectedGraphFromAPoint(adjacencyListConnectedGraph);

		System.out.println("");

		/**
		 *                     0-------3     5------6-----8
		 *                     |       |     |
		 *                     |       |     |
		 *                     |       |     |
		 *                     |       |     |
		 *               1-----2-------4     7
		 */
		ArrayList<ArrayList<Integer>> adjacencyListDisconnectedGraph = new ArrayList<>();
		adjacencyListDisconnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(5, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(6, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(7, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.add(8, new ArrayList<Integer>());
		adjacencyListDisconnectedGraph.get(0).add(2);
		adjacencyListDisconnectedGraph.get(2).add(0);
		adjacencyListDisconnectedGraph.get(1).add(2);
		adjacencyListDisconnectedGraph.get(2).add(1);
		adjacencyListDisconnectedGraph.get(2).add(4);
		adjacencyListDisconnectedGraph.get(4).add(2);
		adjacencyListDisconnectedGraph.get(3).add(4);
		adjacencyListDisconnectedGraph.get(4).add(3);

		adjacencyListDisconnectedGraph.get(5).add(6);
		adjacencyListDisconnectedGraph.get(6).add(5);
		adjacencyListDisconnectedGraph.get(5).add(7);
		adjacencyListDisconnectedGraph.get(7).add(5);
		adjacencyListDisconnectedGraph.get(6).add(8);
		adjacencyListDisconnectedGraph.get(8).add(6);
		dfs.printAllChainNodesInConnectedGraphFromAPoint(adjacencyListDisconnectedGraph);
	}

}

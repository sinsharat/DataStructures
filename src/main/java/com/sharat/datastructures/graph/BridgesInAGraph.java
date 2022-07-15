package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;

public class BridgesInAGraph {
	
	public void findBridgesInAGraph(List<ArrayList<Integer>> graph) {
		if (CollectionUtils.isEmpty(graph)) {
			return;
		}

		int totalVertex = graph.size();
		boolean[] isVisited = new boolean[totalVertex];
		int[] lowValue = new int[totalVertex];
		int[] discoveryTime = new int[totalVertex];
		int[] parentVertex = new int[totalVertex];
		Arrays.fill(parentVertex, -1);
		AtomicInteger at = new AtomicInteger();

		for (int i = 0; i < totalVertex; i++) {
			if (!isVisited[i]) {
				doDFSForBridges(graph, i, parentVertex, isVisited, lowValue, discoveryTime, at);
			}
		}
	}
	
	private void doDFSForBridges(List<ArrayList<Integer>> graph, int parent, int[] parentVertex, boolean[] isVisited,
			int[] lowValue, int[] discoveryTime, AtomicInteger at) {

		isVisited[parent] = true;
		discoveryTime[parent] = lowValue[parent] = at.incrementAndGet();

		List<Integer> adjecencyList = graph.get(parent);
		if (!CollectionUtils.isEmpty(adjecencyList)) {
			for (int current : adjecencyList) {
				if (!isVisited[current]) {
					parentVertex[current] = parent;
					doDFSForBridges(graph, current, parentVertex, isVisited, lowValue, discoveryTime, at);

					lowValue[parent] = Math.min(lowValue[parent], lowValue[current]);

					if (lowValue[current] > discoveryTime[parent]) {
						System.out.println("Edge: " + parent + "-" + current);
					}
				} else if (current != parentVertex[parent]) {
					lowValue[parent] = Math.min(lowValue[parent], discoveryTime[current]);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		/**
		 *        1---0---3
		 *         \  |   |  
		 *          \ |   |
		 *           \|   |
		 *            2   4
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
		adjacencyListConnectedGraph.get(0).add(3);
		adjacencyListConnectedGraph.get(3).add(0);
		adjacencyListConnectedGraph.get(3).add(4);
		adjacencyListConnectedGraph.get(4).add(3);
		
		BridgesInAGraph big = new BridgesInAGraph();
		big.findBridgesInAGraph(adjacencyListConnectedGraph);
	}

}

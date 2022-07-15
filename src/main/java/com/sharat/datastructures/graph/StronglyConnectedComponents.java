package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;

public class StronglyConnectedComponents {

	// Tarjan's Algorithm
	public void findStronglyConnectedComponents(List<ArrayList<Integer>> graph) {
		if (CollectionUtils.isEmpty(graph)) {
			return;
		}
		
		int totalVertices = graph.size();
		Stack<Integer> connectedComponents = new Stack<Integer>();
		int[] discoveryTime = new int[totalVertices];
		int[] lowValue = new int[totalVertices];
		boolean[] stackMember = new boolean[totalVertices];
		AtomicInteger at = new AtomicInteger(0);
		
		for (int i = 0; i < totalVertices; i++) {
			if (discoveryTime[i] == 0) {
				doDFSForStronglyConnectedComponents(graph, i, stackMember, connectedComponents, discoveryTime, lowValue, at);
			}
		}
	}
	
	private void doDFSForStronglyConnectedComponents(List<ArrayList<Integer>> graph, int parent, boolean[] stackMember,
			Stack<Integer> connectedComponents, int[] discoveryTime, int[] lowValue, AtomicInteger at) {
		stackMember[parent] = true;
		connectedComponents.push(parent);
		discoveryTime[parent] = lowValue[parent] = at.incrementAndGet();
		List<Integer> adjecentVertices = graph.get(parent);
		
		if (!CollectionUtils.isEmpty(adjecentVertices)) {
			for (int current: adjecentVertices) {
				if (discoveryTime[current] == 0) {
					doDFSForStronglyConnectedComponents(graph, current, stackMember, connectedComponents, discoveryTime, lowValue, at);
					lowValue[parent] = Math.min(lowValue[parent], lowValue[current]);
				} else if (stackMember[current]) {
					lowValue[parent] = Math.min(lowValue[parent], discoveryTime[current]);
				}
			}
		}
		
		int lowVal = -1;
		if (lowValue[parent] == discoveryTime[parent]) {
			while (lowVal != parent) {
				lowVal = connectedComponents.pop();
				System.out.print(lowVal + " "); 
				stackMember[lowVal] = false; 
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		
		/**
		 *        0<---2--->3
		 *         \   |<\  |  
		 *          \  |  \ |
		 *           \ v   \v
		 *            >1    4
		 */
		ArrayList<ArrayList<Integer>> adjacencyListConnectedGraph = new ArrayList<>();
		adjacencyListConnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListConnectedGraph.get(0).add(1);
		adjacencyListConnectedGraph.get(2).add(0);
		adjacencyListConnectedGraph.get(2).add(1);
		adjacencyListConnectedGraph.get(2).add(3);
		adjacencyListConnectedGraph.get(3).add(4);
		adjacencyListConnectedGraph.get(4).add(2);
		
		StronglyConnectedComponents scc = new StronglyConnectedComponents();
		System.out.println("StronglyConnectedComponents: ");
		scc.findStronglyConnectedComponents(adjacencyListConnectedGraph);
		
		/**
		 * 1--->0---->3
		 * <\   |     |
		 *   \  |     |
		 *    \ |     |
		 *     \v     v
		 *      2     4
		 */
		adjacencyListConnectedGraph = new ArrayList<>();
		adjacencyListConnectedGraph.add(0, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(1, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(2, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(3, new ArrayList<Integer>());
		adjacencyListConnectedGraph.add(4, new ArrayList<Integer>());
		adjacencyListConnectedGraph.get(1).add(0);
		adjacencyListConnectedGraph.get(0).add(2);
		adjacencyListConnectedGraph.get(2).add(1);
		adjacencyListConnectedGraph.get(0).add(3);
		adjacencyListConnectedGraph.get(3).add(4);
		
		System.out.println("StronglyConnectedComponents: ");
		scc.findStronglyConnectedComponents(adjacencyListConnectedGraph);
	}
}

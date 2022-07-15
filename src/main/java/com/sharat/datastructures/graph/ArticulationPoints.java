package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;

public class ArticulationPoints {

	// articulation point is a point which when removed will make the graph disconnected
	public void findArticulationPoints(List<ArrayList<Integer>> graph) {
		if (CollectionUtils.isEmpty(graph)) {
			return;
		}

		int totalVertices = graph.size();
		boolean visited[] = new boolean[totalVertices];
		int discoveryTime[] = new int[totalVertices];
		int lowValue[] = new int[totalVertices];
		int parent[] = new int[totalVertices];
		boolean ap[] = new boolean[totalVertices];

		Arrays.fill(parent, -1);
		AtomicInteger atomicInt = new AtomicInteger(0);

		for (int i = 0; i < totalVertices; i++) {
			if (!visited[i]) {
				doDFSForAP(graph, i, visited, discoveryTime, lowValue, parent, ap, atomicInt);
			}
		}

		for (int i = 0; i < totalVertices; i++) {
			if (ap[i]) {
				System.out.print(i + " ");
			}
		}
	}

	private void doDFSForAP(List<ArrayList<Integer>> graph, int parentVertex, boolean visited[], int discoveryTime[], int lowValue[], int parent[],
			boolean ap[], AtomicInteger atomicInt) {
		int children = 0;
		visited[parentVertex] = true;
		discoveryTime[parentVertex] = lowValue[parentVertex] = atomicInt.incrementAndGet();
		List<Integer> adjecencyList = graph.get(parentVertex);
		if (!CollectionUtils.isEmpty(adjecencyList)) {
			for (int currentVertex : adjecencyList) {
				if (!visited[currentVertex]) {
					children++;
					parent[currentVertex] = parentVertex;
					doDFSForAP(graph, currentVertex, visited, discoveryTime, lowValue, parent, ap, atomicInt);

					// low value of parent vertex will be the lowest value of parent and child vertex.
					// this is done to check if the children vertex are backtracking to parent vertices
					// then it child vertex will have low val than its parent. In such cases even parent
					// can access its ancestors from another path
					lowValue[parentVertex] = Math.min(lowValue[parentVertex], lowValue[currentVertex]);

					// if root vertex and has more than 1 branches then its an articulate point
					if (parent[parentVertex] == -1 && children > 1)
						ap[parentVertex] = true;
					
					// if not root vertex and any ancestor is not reachable 
					// then lowValue of current vertex will be higher than or
					// equal to discovery point of its parent vertex for an articulate point.
					if (parent[parentVertex] != -1 && lowValue[currentVertex] >= discoveryTime[parentVertex])
						ap[parentVertex] = true;
				} else if (currentVertex != parent[parentVertex]) {
					// if parents of the vertex already visited is not same, i.e. there is a cycle,
					// then the low value of parent vertex should be lower value of discovery time of child vertex 
					// and low value of parent. this is done to update low value of child reaches its ancestor.
					lowValue[parentVertex] = Math.min(lowValue[parentVertex], discoveryTime[currentVertex]);
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
		
		ArticulationPoints ap = new ArticulationPoints();
		ap.findArticulationPoints(adjacencyListConnectedGraph);
	}
}

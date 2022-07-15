package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections4.CollectionUtils;

public class ShortestPathInWeightedDirectedGraph {

	// Bellman ford's algorithm works for negative weights for directed and undirected O(VE)
	public int[] findShortestPathFromAGivenSourceUsingBellmanFordShortestPathAlgorithm(List<ArrayList<Edge>> vertexEdgeList,
			int startNumber) {
		if (CollectionUtils.isEmpty(vertexEdgeList)) {
			return null;
		}
		int size = vertexEdgeList.size();
		int[] shortestDistance = new int[size];
		Arrays.fill(shortestDistance, Integer.MAX_VALUE);
		shortestDistance[startNumber] = 0;

		int destVertex;
		int distanceFromSourceVertex;
		int startVertex;
		for (int i = 0; i < size; i++) {
			for (ArrayList<Edge> edgeList : vertexEdgeList) {
				if (!CollectionUtils.isEmpty(edgeList)) {
					for (Edge edge : edgeList) {
						startVertex = edge.getStartVertex();
						destVertex = edge.getEndVertex();
						if (shortestDistance[startVertex] != Integer.MAX_VALUE) {
							distanceFromSourceVertex = edge.getWeight() + shortestDistance[startVertex];
							if (shortestDistance[destVertex] > distanceFromSourceVertex) {
								shortestDistance[destVertex] = distanceFromSourceVertex;
							}
						}
					}
				}
			}
		}
		return shortestDistance;
	}
	
	/*
	 * O(V+E)
	 */
	public int[] findShortestDistanceFromAGivenPointUsingTopologicalSorting(List<ArrayList<Edge>> vertexEdgeList,
			int startNumber) {
		if (CollectionUtils.isEmpty(vertexEdgeList)) {
			return null;
		}

		int size = vertexEdgeList.size();
		int[] shortestDistance = new int[size];
		for (int i = 0; i < size; i++) {
			shortestDistance[i] = Integer.MAX_VALUE;
		}

		int endVertex;
		int[] vertexDependency = new int[size];
		for (ArrayList<Edge> edgeList : vertexEdgeList) {
			if (!CollectionUtils.isEmpty(edgeList)) {
				for (Edge e : edgeList) {
					endVertex = e.getEndVertex();
					vertexDependency[endVertex]++;
				}
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			if (vertexDependency[i] == 0) {
				queue.add(i);
			}
		}

		int[] topologicalSortOrder = new int[size];
		int startVertex;
		List<Edge> edgeList;
		int c = 0;
		while (!queue.isEmpty()) {
			startVertex = queue.poll();
			topologicalSortOrder[c++] = startVertex;
			edgeList = vertexEdgeList.get(startVertex);
			if (!CollectionUtils.isEmpty(edgeList)) {
				for (Edge edge : edgeList) {
					endVertex = edge.getEndVertex();
					vertexDependency[endVertex]--;
					if (vertexDependency[endVertex] == 0) {
						queue.add(endVertex);
					}
				}
			}
		}

		int curVertex;
		List<Edge> adjecentEgdeList;
		shortestDistance[startNumber] = 0;
		for (int i = startNumber; i < size; i++) {
			curVertex = topologicalSortOrder[i];
			adjecentEgdeList = vertexEdgeList.get(curVertex);
			if (!CollectionUtils.isEmpty(adjecentEgdeList)) {
				for (Edge e : adjecentEgdeList) {
					endVertex = e.getEndVertex();
					shortestDistance[endVertex] = Math.min(shortestDistance[curVertex] + e.getWeight(),
							shortestDistance[endVertex]);
				}
			}
		}

		return shortestDistance;
	}
	
	/*
	 * O(VE)
	 */
	public int[] findShortestDistanceFromAGivenPoint(List<ArrayList<Edge>> vertexEdgeList, int startNumber) {

		if (CollectionUtils.isEmpty(vertexEdgeList)) {
			return null;
		}

		int size = vertexEdgeList.size();
		int[] shortestDistance = new int[size];
		boolean[] isVisited = new boolean[size];
		for (int i = 0; i < size; i++) {
			shortestDistance[i] = Integer.MAX_VALUE;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNumber);
		shortestDistance[startNumber] = 0;
		isVisited[startNumber] = true;
		int currentVertex;
		int endVertex;
		List<Edge> adjecentEdgeList;
		while (!queue.isEmpty()) {
			currentVertex = queue.poll();
			adjecentEdgeList = vertexEdgeList.get(currentVertex);
			if (!CollectionUtils.isEmpty(adjecentEdgeList)) {
				for (Edge edge : adjecentEdgeList) {
					endVertex = edge.getEndVertex();
					shortestDistance[endVertex] = Math.min(
							shortestDistance[currentVertex] + edge.getWeight(),
							shortestDistance[endVertex]);
					if (!isVisited[endVertex]) {
						queue.add(endVertex);
						isVisited[endVertex] = true;
					}
				}
			}
		}
		return shortestDistance;
	}

	private Edge addEdge(int startVertex, int endVertex, int weight) {
		Edge edge = new Edge(startVertex, endVertex, weight);
		return edge;
	}

	public static void main(String[] args) {
		ShortestPathInWeightedDirectedGraph spwg = new ShortestPathInWeightedDirectedGraph();

		/**
 		 *    1    3    6
		 * 0---->1--->2----->3 
		 *  \    ^   />     />
		 *  1\  1| 2/     1/
		 *    \  | /      /
		 *     \ |/  4   /
		 *      >4---->5
		 */
		List<ArrayList<Edge>> vertexEdgeList = new ArrayList<ArrayList<Edge>>();
		vertexEdgeList.add(0, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(1, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(2, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(3, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(4, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(5, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.get(0).add(spwg.addEdge(0, 1, 1));
		vertexEdgeList.get(0).add(spwg.addEdge(0, 4, 1));
		vertexEdgeList.get(1).add(spwg.addEdge(1, 2, 3));
		vertexEdgeList.get(2).add(spwg.addEdge(2, 3, 6));
		vertexEdgeList.get(4).add(spwg.addEdge(4, 1, 1));
		vertexEdgeList.get(4).add(spwg.addEdge(4, 2, 2));
		vertexEdgeList.get(4).add(spwg.addEdge(4, 5, 4));
		vertexEdgeList.get(5).add(spwg.addEdge(5, 3, 1));
		System.out.println("Shortest Disance findShortestPathFromAGivenSourceUsingBellmanFordShortestPathAlgorithm for " + vertexEdgeList + "from Vertex 0: ");
		int[] shortestDistances = spwg.findShortestPathFromAGivenSourceUsingBellmanFordShortestPathAlgorithm(vertexEdgeList, 0);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();
		System.out.println("Shortest Disance findShortestDistanceFromAGivenPointUsingTopologicalSorting for " + vertexEdgeList + "from Vertex 0: ");
		shortestDistances = spwg.findShortestDistanceFromAGivenPointUsingTopologicalSorting(vertexEdgeList, 0);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();
		
		System.out.println("Shortest Disance findShortestDistanceFromAGivenPoint for " + vertexEdgeList + "from Vertex 0: ");
		shortestDistances = spwg.findShortestDistanceFromAGivenPoint(vertexEdgeList, 0);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();

		/**
		 *    1       3
		 * 0----->1------>2
		 *         \     /
		 *         2\   /4
		 *           >3<
		 */
		vertexEdgeList = new ArrayList<ArrayList<Edge>>();
		vertexEdgeList.add(0, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(1, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(2, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.add(3, new ArrayList<ShortestPathInWeightedDirectedGraph.Edge>());
		vertexEdgeList.get(0).add(spwg.addEdge(0, 1, 1));
		vertexEdgeList.get(1).add(spwg.addEdge(1, 2, 3));
		vertexEdgeList.get(1).add(spwg.addEdge(1, 3, 2));
		vertexEdgeList.get(2).add(spwg.addEdge(2, 3, 3));
		System.out.println("Shortest Disance findShortestPathFromAGivenSourceUsingBellmanFordShortestPathAlgorithm for " + vertexEdgeList + "from Vertex 1: ");
		shortestDistances = spwg.findShortestPathFromAGivenSourceUsingBellmanFordShortestPathAlgorithm(vertexEdgeList, 1);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();
		System.out.println("Shortest Disance findShortestDistanceFromAGivenPointUsingTopologicalSorting for " + vertexEdgeList + "from Vertex 1: ");
		shortestDistances = spwg.findShortestDistanceFromAGivenPointUsingTopologicalSorting(vertexEdgeList, 1);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();
		System.out.println("Shortest Disance findShortestDistanceFromAGivenPoint for " + vertexEdgeList + "from Vertex 1: ");
		shortestDistances = spwg.findShortestDistanceFromAGivenPoint(vertexEdgeList, 1);
		for (int shortestDistance: shortestDistances) {
			System.out.print(shortestDistance + " ");
		}
		System.out.println();
	}

	public class Edge {
		int startVertex;
		int endVertex;
		int weight;

		public Edge() {
			super();
		}

		public Edge(int startVertex, int endVertex, int weight) {
			super();
			this.startVertex = startVertex;
			this.endVertex = endVertex;
			this.weight = weight;
		}

		public int getStartVertex() {
			return startVertex;
		}

		public void setStartVertex(int startVertex) {
			this.startVertex = startVertex;
		}

		public int getEndVertex() {
			return endVertex;
		}

		public void setEndVertex(int endVertex) {
			this.endVertex = endVertex;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

}

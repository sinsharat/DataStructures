package com.sharat.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class MinimumSpanningTree {
	
	// Kruskal's Algorithm
	public int minimumSpanningTreeKruskalAlgorithm(List<Edge> adjecencyGraph, int totalVertex) {
		if (CollectionUtils.isEmpty(adjecencyGraph)) {
			return 0;
		}

		Collections.sort(adjecencyGraph);

		List<Edge> resultEdges = new ArrayList<Edge>();
		// Allocate memory for creating V subsets
		Subset[] subsets = new Subset[totalVertex];
		for (int i = 0; i < totalVertex; ++i) {
			subsets[i] = new Subset();
			subsets[i].parent = i;
			subsets[i].rank = 0;
		}

		Edge edge;
		int res = 0;
		int resultEdgeCount = 0;
		int index = 0; // Index used to pick next edge
		// Number of edges to be taken is equal to V-1
		while (resultEdgeCount < totalVertex - 1) {
			// Step 2: Pick the smallest edge. And increment
			// the index for next iteration
			edge = adjecencyGraph.get(index++);

			int x = find(subsets, edge.startVertex);
			int y = find(subsets, edge.endVertex);

			// If including this edge does't cause cycle,
			// include it in result and increment the index
			// of result for next edge
			if (x != y) {
				resultEdges.add(edge);
				resultEdgeCount++;
				union(subsets, x, y);

				res += edge.weight;
			}
			// Else discard the next_edge
		}

		return res;
	}

	// A utility function to find set of an element i
	// (uses path compression technique)
	private int find(Subset subsets[], int i) {
		// find root and make root as parent of i (path compression)
		if (subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}

	// (uses union by rank)
	private void union(Subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		// Attach smaller rank tree under root of high rank tree
		// (Union by Rank)
		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[xroot].rank > subsets[yroot].rank) {
			subsets[yroot].parent = xroot;

		// If ranks are same, then make one as root and increment
		// its rank by one
		} else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	// Given a weighted undirected graph. Also meaning connecting all vertices in total minimum path
	// Prim's Algorithm
	// Note: Time complexity can be reduced by using adjecencyList with Min heap(Priority Queue) instead of matrix
	public int minimumSpanningTree(int[][] graph) {

		if (graph == null || graph.length == 0) {
			return 0;
		}
		int mst = 0;
		int size = graph.length;
		boolean[] isVisited = new boolean[size];
		isVisited[0] = true;
		int includedVertex = 1;
		int shortestDistanceConnectedNode;
		int shortestDistanceNode = -1;
		// run loop all vertex is included
		while (includedVertex < size) {
			shortestDistanceConnectedNode = Integer.MAX_VALUE;
			// run a loop for included vertex
			for (int i = 0; i < size; i++) {
				if (!isVisited[i]) {
					continue;
				}
				// run a loop for pending vertex
				for (int j = 0; j < size; j++) {
					if (isVisited[j]) {
						continue;
					}
					// find min value from included vertex to un-included vertex(min weight edge) 
					if (graph[i][j] != 0 && shortestDistanceConnectedNode > graph[i][j]) {
						shortestDistanceConnectedNode = graph[i][j];
						shortestDistanceNode = j;
					}
				}
			}

			// once min weight edge is found, add un-include vertex 
			// to included vertex list and add the edge weight to result
			if (shortestDistanceConnectedNode != Integer.MAX_VALUE) {
				mst += shortestDistanceConnectedNode;
				isVisited[shortestDistanceNode] = true;
				includedVertex++;
			}
		}

		return mst;
	}
	
	private Edge addEdge(int startVertex, int endVertex, int weight) {
		Edge edge = new Edge(startVertex, endVertex, weight);
		return edge;
	}
	
	public static void main(String[] args) {
		MinimumSpanningTree mst = new MinimumSpanningTree();

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
		int minSpanDistance = mst.minimumSpanningTree(graph);
		System.out.println("MinimumSpanningTree: " + minSpanDistance);
		
		List<Edge> adjecencyGraph = new ArrayList<Edge>();
		adjecencyGraph.add(mst.addEdge(0, 1, 1));
		adjecencyGraph.add(mst.addEdge(0, 4, 4));
		adjecencyGraph.add(mst.addEdge(1, 4, 5));
		adjecencyGraph.add(mst.addEdge(1, 2, 3));
		adjecencyGraph.add(mst.addEdge(2, 4, 2));
		adjecencyGraph.add(mst.addEdge(2, 3, 6));
		adjecencyGraph.add(mst.addEdge(3, 5, 5));
		adjecencyGraph.add(mst.addEdge(4, 5, 7));
		minSpanDistance = mst.minimumSpanningTreeKruskalAlgorithm(adjecencyGraph, 6);
		System.out.println("MinimumSpanningTree: " + minSpanDistance);
	}
	
	class Edge implements Comparable<Edge>{
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

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [startVertex=");
			builder.append(startVertex);
			builder.append(", endVertex=");
			builder.append(endVertex);
			builder.append(", weight=");
			builder.append(weight);
			builder.append("]");
			return builder.toString();
		}
	}
	
	// A class to represent 
	// a subset for union-find
	class Subset {
		int parent, rank;

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Subset [parent=");
			builder.append(parent);
			builder.append(", rank=");
			builder.append(rank);
			builder.append("]");
			return builder.toString();
		}
	};
}

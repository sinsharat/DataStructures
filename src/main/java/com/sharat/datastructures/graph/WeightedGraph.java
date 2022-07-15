package com.sharat.datastructures.graph;

public class WeightedGraph {
	private static final int INFINITY = -1;
	private static final int MAX_SIZE = 20;
	private WVertex[] vertexList;
	private int[][] adjList;
	private int size;
	private boolean directed;
	
	public WeightedGraph() {
		this(false);
	}
	
	public WeightedGraph(boolean directed) {
		this.directed = directed;
		size = 0;
		vertexList = new WVertex[MAX_SIZE];
		adjList = new int[MAX_SIZE][MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				adjList[i][j] = INFINITY;
			}
		}
	}
	
	public void addVertex(char label) {
		vertexList[size++] = new WVertex(label);
	}
	
	public void addEdge(int src, int dest, int dist)  {
		adjList[src][dest] = dist;
		if (!directed) {
			adjList[dest][src] = dist;
		}
	}
	
	public void displayVertex(int index) {
		System.out.print(vertexList[index].getLabel());
	}
	
	public void mstw() {
		int currentVertex = 0;
		PriorityQueue pq = new PriorityQueue();
		WVertex[] vertexList = this.vertexList;
		int size = this.size;
		int nTree = 0;
		vertexList[currentVertex].setVisited(true);
		int dist = INFINITY;
		while(nTree < size - 1) {
			for (int j = 0; j < size; j++) {
				if (j == currentVertex) {
					continue;
				}
				if (vertexList[j].isVisited()) {
					continue;
				}
				dist = adjList[currentVertex][j];
				if (dist == INFINITY) {
					continue;
				}
				putInPQ(pq, currentVertex, j, dist);
			}
			if (pq.size() <= 0) {
				System.out.println("Graph is disconnected.");
				return;
			}
			nTree++;
			Edge e = pq.poll();
			int srcVertex = e.getSrcVertex();
			int destVertex = e.getDestVertex();
			vertexList[destVertex].setVisited(true);
			displayVertex(srcVertex);
			displayVertex(destVertex);
			System.out.print(" ");
			currentVertex = destVertex;
		}
		for (int i = 0; i < size; i++) {
			vertexList[i].setVisited(false);
		}
	}
	
	public void allPairsShortestPath() {
		int size = this.size;
		Path[] sPath;
		for (int i = 0; i < size; i++) {
			System.out.print("\t" + vertexList[i].getLabel());
		}
		System.out.println();
		for (int i = 0; i < size; i++) {
			sPath = shortestPath(i);
			System.out.print(vertexList[i].getLabel() + "\t");
			for (int j= 0; j < size; j++) {
				System.out.print(( i == j || sPath[j].getDistance() == INFINITY ? "-" : sPath[j].getDistance()) + "\t");
			}
			System.out.println();
		}
	}
	
	public void displayShortextPath(int srcVertex) {
		Path[] sPath = shortestPath(srcVertex);
		displayPaths(vertexList, sPath);
	}
	
	private Path[] shortestPath(int startVertex) {
		int currentVertex = startVertex;
		int nTree = 1;
		int size = this.size;
		int[][] adjList = this.adjList;
		WVertex[] vertexList = this.vertexList;
		Path[] sPath = new Path[size];
		for (int j = 0; j < size; j++) {
			sPath[j] = new Path(currentVertex, adjList[currentVertex][j]);
		}
		vertexList[currentVertex].setVisited(true);
		while (nTree < size) {
			currentVertex = getMinPathVertex(sPath, vertexList);
			if (currentVertex == INFINITY) {
				// Not all paths are connected.
				break;
			}
			int pathToCurrent = sPath[currentVertex].getDistance();
			vertexList[currentVertex].setVisited(true);
			adjustPath(sPath, pathToCurrent, currentVertex);
			nTree++;
		}
		for (int i = 0; i < size; i++) {
			vertexList[i].setVisited(false);
		}
		return sPath;
	}

	private void displayPaths(WVertex[] vertexList, Path[] sPath) {
		for (int i = 0; i < sPath.length; i++) {
			System.out.print(vertexList[i].getLabel() + "=" + (sPath[i].getDistance() != INFINITY ? sPath[i].getDistance() : "inf") + "(" + vertexList[sPath[i].getParentVertex()].getLabel()+") ");
		}
	}
	
	private void adjustPath(Path[] sPath, int pathToCurrent, int currentVertex) {
		int totalDist = INFINITY;
		int pathToDest = INFINITY;
		for (int j = 0; j < sPath.length; j++) {
			pathToDest = adjList[currentVertex][j];
			if (pathToDest != INFINITY && (sPath[j].getDistance() > (totalDist = pathToCurrent + pathToDest) || sPath[j].getDistance() == INFINITY)) {
				sPath[j] = new Path(currentVertex, totalDist);
			}
		}
	}

	private int getMinPathVertex(Path[] sPath, WVertex[] vertexList) {
		int min = Integer.MAX_VALUE;
		int destVertex = INFINITY;
		for (int i = 0; i < sPath.length; i++) {
			if (!vertexList[i].isVisited() && (sPath[i].getDistance() != INFINITY && min > sPath[i].getDistance())) {
				min = sPath[i].getDistance();
				destVertex = i;
			}
		}
		return destVertex;
	}

	private void putInPQ(PriorityQueue pq, int srcVertex, int destVertex, int dist) {
		int pqDestIndex = pq.find(destVertex);
		if (pqDestIndex != INFINITY) {
			Edge e = pq.getEntry(pqDestIndex);
			if (dist < e.getDistance()) {
				pq.remove(pqDestIndex);
				pq.offer(new Edge(srcVertex, destVertex, dist));
			}
		} else {
			pq.offer(new Edge(srcVertex, destVertex, dist));
		}
	}
	
	public static void main(String[] args) {
		// weighted minimum spanning tree
		WeightedGraph theWeightedGraph = new WeightedGraph(true);
		theWeightedGraph.addVertex('A'); // 0 (start)
		theWeightedGraph.addVertex('B'); // 1
		theWeightedGraph.addVertex('C'); // 2
		theWeightedGraph.addVertex('D'); // 3
		theWeightedGraph.addVertex('E'); // 4
		theWeightedGraph.addEdge(0, 1, 50); // AB 50
		theWeightedGraph.addEdge(0, 3, 80); // AD 80
		theWeightedGraph.addEdge(1, 2, 60); // BC 60
		theWeightedGraph.addEdge(1, 3, 90); // BD 90
		theWeightedGraph.addEdge(2, 4, 40); // CE 40
		theWeightedGraph.addEdge(3, 2, 20); // DC 20
		theWeightedGraph.addEdge(3, 4, 70); // DE 70
		theWeightedGraph.addEdge(4, 1, 50); // EB 50
		System.out.print("Minimum spanning tree: ");
		theWeightedGraph.mstw(); // minimum spanning tree
		System.out.println();
		System.out.println("Shortest paths: ");
		theWeightedGraph.displayShortextPath(1); // shortest paths
		System.out.println();
		System.out.println("All Pairs Shortest path: ");
		theWeightedGraph.allPairsShortestPath(); // shortest paths
		System.out.println();
	}
}

class WVertex {
	private char label;
	private boolean visited;
	
	public WVertex(char label) {
		this.label = label;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public char getLabel() {
		return label;
	}
}

class Path {
	private int parentVertex;
	private int distance;
	
	public Path (int parentVertex, int distance) {
		this.parentVertex = parentVertex;
		this.distance = distance;
	}
	
	public int getParentVertex() {
		return parentVertex;
	}
	
	public int getDistance() {
		return distance;
	}
}

class Edge {
	private int srcVertex;
	private int destVertex;
	private int distance;
	
	public Edge (int srcVertex, int destVertex, int distance) {
		this.srcVertex = srcVertex;
		this.destVertex = destVertex;
		this.distance = distance;
	}
	
	public int getSrcVertex() {
		return srcVertex;
	}
	
	public int getDestVertex() {
		return destVertex;
	}
	
	public int getDistance() {
		return distance;
	}
}

class PriorityQueue {
	private static final int MAX_SIZE = 20;
	private static final int INFINITY = -1;
	private Edge[] pq;
	private int size;
	
	public PriorityQueue() {
		size = 0;
		pq =  new Edge[MAX_SIZE];
	}
	
	public void offer(Edge e) {
		int index = size; 
		for (int i = 0; i < size; i++) {
			if (pq[i].getDistance() < e.getDistance()) {
				index = i;
				break;
			}
		}
		
		for (int i = size - 1; i >= index; i--) {
			pq[i+1] = pq[i];
		}
		
		size++;
		pq[index] = e;
	}
	
	public Edge poll() {
		return pq[--size];
	}
	
	public Edge getEntry(int index) {
		return pq[index];
	}
	
	public Edge remove(int index) {
		Edge e = pq[index];
		for (int i = index; i < size - 1; i++) {
			pq[i] = pq[i+1];
		}
		return e;
	}
	
	public int find(int destVertex) {
		for (int j = 0; j < size; j++) {
			if (pq[j].getDestVertex() == destVertex) {
				return j;
			}
		}
		return INFINITY;
	}
	
	public int size() {
		return size;
	}
}

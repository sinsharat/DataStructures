package com.sharat.datastructures.graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Graph {

	private static final int INFINITY = -1;
	private static final int MAX_SIZE = 20;
	private Vertex[] vertexList;
	private int[][] adjList;
	private int size;
	private boolean isDirected;
	
	public Graph(boolean isDirected) {
		this.isDirected = isDirected;
		vertexList = new Vertex[MAX_SIZE];
		adjList = new int[MAX_SIZE][MAX_SIZE];
		size = 0;
		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				adjList[i][j] = INFINITY;
			}
		}
	}
	
	public void addVertex(char label) {
		vertexList[size++] = new Vertex(label);
	}
	
	public void addEdge(int srcVertex, int destVertex) {
		addEdge(srcVertex, destVertex, 1);
	}
	
	public void addEdge(int srcVertex, int destVertex, int weight) {
		adjList[srcVertex][destVertex] = weight;
		if (!isDirected) {
			adjList[destVertex][srcVertex] = weight;
		}
	}
	
	public void bfs() {
		Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		int currentVertex = 0;
		vertexList[currentVertex].isVisited = true;
		displayVertex(currentVertex);
		queue.offer(currentVertex);
		while (!queue.isEmpty()) {
			while ((currentVertex = getAdjVertex(queue.peek())) != INFINITY) {
				vertexList[currentVertex].isVisited = true;
				displayVertex(currentVertex);
				queue.offer(currentVertex);
			}
			queue.poll();
		}
		
		for (int i = 0; i < size; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	public void dfs() {
		Stack<Integer> stack = new Stack<Integer>();
		int currentVertex = 0;
		vertexList[currentVertex].isVisited = true;
		displayVertex(currentVertex);
		stack.push(currentVertex);
		while(!stack.isEmpty()) {
			currentVertex = getAdjVertex(stack.peek());
			if (currentVertex == INFINITY) {
				stack.pop();
				continue;
			}
			vertexList[currentVertex].isVisited = true;
			displayVertex(currentVertex);
			stack.push(currentVertex);
		}
		
		for (int i = 0; i < size; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	public void mst() {
		Stack<Integer> stack = new Stack<Integer>();
		int srcVetex = 0;
		int destVertex = 0;
		vertexList[srcVetex].isVisited = true;
		stack.push(srcVetex);
		while (!stack.isEmpty()) {
			srcVetex = stack.peek();
			destVertex = getAdjVertex(srcVetex);
			if (destVertex == INFINITY) {
				stack.pop();
				continue;
			}
			vertexList[destVertex].isVisited = true;
			displayVertex(srcVetex);
			displayVertex(destVertex);
			System.out.print(" ");
			stack.push(destVertex);
		}
		
		for (int i = 0; i < size; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	public void topoSort() {
		int size = this.size;
		char []topoSorted = new char[size];
		Vertex[] vertList = vertexList;
		int[][] adjList = this.adjList;
		while (size > 0) {
			int noSuccessor = getNoSuccessor(size, adjList);
			if (noSuccessor == INFINITY) {
				System.err.println("ERROR : Graph has cycles.");
				return;
			}
			
			topoSorted[size - 1] = vertList[noSuccessor].label;
			removeVertex(vertList, adjList, size, noSuccessor);
			size--;
		}
		
		for (char label : topoSorted) {
			System.out.print(label);
		}
	}
	
	private int getNoSuccessor(int size, int[][] adjList) {
		boolean isNoSuccessor;
		for (int i = 0; i < size; i++) {
			isNoSuccessor = true;
			for (int j = 0; j < size; j++) {
				if (adjList[i][j] != INFINITY) {
					isNoSuccessor = false;
					break;
				}
			}
			if (isNoSuccessor) {
				return i;
			}
		}
		return INFINITY;
	}
	
	private void removeVertex(Vertex[] vertList, int[][] adjList, int size, int vertexIndex) {
		for (int i = vertexIndex; i < size - 1; i++) {
			vertList[i] = vertList[i + 1];
		}
		removeRow(adjList, size, vertexIndex);
		removeColumn(adjList, size, vertexIndex);
	}
	
	private void removeRow(int[][] adjList, int size, int row) {
		for (int i = row; i < size - 1; i++) {
			for (int j = 0; j < size; j++) {
				adjList[i][j] = adjList[i+1][j];
			}
		}
	}
	
	private void removeColumn(int[][] adjList, int size, int column) {
		for (int i = 0; i < size - 1; i++) {
			for (int j = column; j < size; j++) {
				adjList[i][j] = adjList[i][j + 1];
			}
		}
	}
	
	private int getAdjVertex(int currentVertex) {
		for (int j = 0; j < size; j++) {
			if (!vertexList[j].isVisited && adjList[currentVertex][j] != INFINITY) {
				return j;
			}
		}
		return INFINITY;
	}
	
	private void displayVertex(int vertexIndex) {
		System.out.print(vertexList[vertexIndex].label);
	}
	
	public static void main(String []args) {
		Graph theGraph = new Graph(true);
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addVertex('F'); // 5
		theGraph.addVertex('G'); // 6
		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE
		theGraph.addEdge(1, 5); // BF
		theGraph.addEdge(2, 6); // CG
		theGraph.addEdge(4, 2); // EC
		System.out.print("BFS Visits: ");
		theGraph.bfs(); // breadth-first search
		System.out.println();
		System.out.print("DFS Visits: ");
		theGraph.dfs(); // depth-first search
		System.out.println();
		System.out.print("MST Visits: ");
		theGraph.mst(); // minimum spanning trees
		System.out.println();
		System.out.print("Topologically sorted order:");
		theGraph.topoSort();
		System.out.println();
	}
}

class Vertex {
	public char label;
	public boolean isVisited;
	
	public Vertex(char label) {
		this.label = label;
	}
}
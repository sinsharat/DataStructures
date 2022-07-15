package com.sharat.datastructures.tree;

public class HeightOfBinaryTree {
	
	public static class Node {
        int data;
        
        Node left, right = null;
        
        public Node(int data) {
            this.data = data;
        }
    }
	
	public int findMaximumHeight(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(findMaximumHeight(node.left), findMaximumHeight(node.right));
	}
	
	public static void main (String[] args) {
        /*
         *             20
         *           /    \
         *          8      12
         *         / \     / 
         *        3   5   8
         *               /
         *              1
         */
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(12);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(8);
        root.right.left.left = new Node(1);
        
        HeightOfBinaryTree heightOfBinaryTree = new HeightOfBinaryTree();
        System.out.println(heightOfBinaryTree.findMaximumHeight(root));
    }

}

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures.tree;

/**
 * BalancedHeightTree.java
 *
 * @author S00742735
 * @since 2020-09-22
 */
//a tree is said to be height balanced when at each node difference in height on the left side and height on the right side <=1
public class BalancedHeightTree {

    
    public static class Node {
        int data;
        
        Node left, right = null;
        
        public Node(int data) {
            this.data = data;
        }
    }
    
    public int checkBalancedHeight(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	
    	int lh = checkBalancedHeight(node.left);
    	if (lh == -1) {
    		return -1;
    	}
    	
    	int rh = checkBalancedHeight(node.right);
    	if (lh == -1) {
    		return -1;
    	}
    	
    	if (Math.abs(lh-rh) < 2) {
    		return Math.max(lh, rh) + 1;
    	}
    	
    	return -1;
    }
    
    /**
     * isBalancedHeight 
     * @param root
     * @return boolean
     */
    private boolean isBalancedHeight(Node root) {
        return checkBalancedHeight(root) > 0;
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
        
        BalancedHeightTree childrenSumTree = new BalancedHeightTree();
        System.out.println(childrenSumTree.isBalancedHeight(root));
    }

}

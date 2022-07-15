/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BinaryTreeWidth.java
 *
 * @author S00742735
 * @since 2020-09-22
 */
public class BinaryTreeWidth {
    
    public static class Node {
        int data;
        
        Node left, right = null;
        
        public Node(int data) {
            this.data = data;
        }
    }

    public int getBinaryTreeWidth(Node root) {
        if (null == root) {
            return 0;
        }
        
        int maxWidth = 0;
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        
        int queueSize = 0;
        Node node = null;
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            maxWidth = Math.max(maxWidth, queueSize);
            for (int i = 0; i < queueSize; i++) {
                node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
        }
        
        return maxWidth;
        
    }
    
    
    public static void main (String[] args) {
        /*
         *             20
         *           /    \
         *          8      12
         *         / \     / \
         *        3   5   8   9
         *               /
         *              1
         */
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(12);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(8);
        root.right.right = new Node(9);
        root.right.left.left = new Node(1);
        
        BinaryTreeWidth binaryTreeWidth = new BinaryTreeWidth();
        System.out.println(binaryTreeWidth.getBinaryTreeWidth(root));
    }
}

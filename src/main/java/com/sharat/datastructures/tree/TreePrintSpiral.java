/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures.tree;

import java.util.Stack;

/**
 * TreePrintSpiral.java
 *
 * @author S00742735
 * @since 2020-09-23
 */
// spiral form means traversal printing of node levels from left to right and then next level from right to left and so on 
public class TreePrintSpiral {

    // A Binary Tree Node
    static class Node {
        int data;

        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public void printSprial(Node root) {
        Stack<Node> reverseStack = new Stack<Node>();
        Stack<Node> orderdStack = new Stack<Node>();
        orderdStack.add(root);
        
        Node node = null;
        int stackSize = 0;
        boolean reverse = false;
        while (orderdStack.size() > 0 || reverseStack.size() > 0) {
            if (!reverse) {
                stackSize = orderdStack.size();
                for (int i = 0; i < stackSize; i++) {
                    node = orderdStack.pop();
                    System.out.print(node.data + " ");
                    if (node.left != null) {
                        reverseStack.push(node.left);
                    }
                    if (node.right != null) {
                        reverseStack.push(node.right);
                    }
                }
            } else {
                stackSize = reverseStack.size();
                for (int i = 0; i < stackSize; i++) {
                    node = reverseStack.pop();
                    System.out.print(node.data + " ");
                    if (node.right != null) {
                        orderdStack.push(node.right);
                    }
                    if (node.left != null) {
                        orderdStack.push(node.left);
                    }
                }
            }
            System.out.println();
            reverse = !reverse;
        }
    }

    public static void main(String args[]) {
        // Let us create binary tree shown in above diagram
        Node root = new Node(7);
        root.left = new Node(6);
        root.right = new Node(5);
        root.left.left = new Node(8);
        root.left.left.left = new Node(4);
        root.left.left.right = new Node(9);
        root.left.right = new Node(3);
        root.right.left = new Node(2);
        root.right.right = new Node(1);
        /*
         *              7 
         *            /   \ 
         *           6     5 
         *          / \   / \ 
         *         8   3 2   1 
         *        /     \
         *       4       9
         */

        TreePrintSpiral treePrintSpiral = new TreePrintSpiral();
        treePrintSpiral.printSprial(root);
    }

}

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures.tree;

/**
 * ReverseTree.java
 *
 * @author S00742735
 * @since 2020-09-22
 */
public class InvertBinaryTree {// A node contains the value, left and right pointers

    static class Node {
        int data;

        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
    
    Node invert(Node node) {
        if (node == null)
            return node;

        /* recursive calls */
        Node left = invert(node.left);
        Node right = invert(node.right);

        /* swap the left and right pointers */
        node.left = right;
        node.right = left;

        return node;
    }

    // print InOrder binary tree traversal.
    void print_tree(Node node) {
        if (node == null)
            return;

        print_tree(node.left);
        System.out.print(node.data + " ");

        print_tree(node.right);
    }

    /* testing for example nodes */
    public static void main(String args[]) {
        /* creating a binary tree and entering the nodes */
        InvertBinaryTree tree = new InvertBinaryTree();
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(4);
        root.right.left = new Node(3);
        root.right.right = new Node(5);

        /* print inorder traversal of the input tree */
        System.out.println("Inorder traversal of input tree is :");
        tree.print_tree(root);
        System.out.println("");

        /* invert tree */
        tree.invert(root);

        /* print inorder traversal of the minor tree */
        System.out.println("Inorder traversal of binary tree is : ");
        tree.print_tree(root);

    }
}

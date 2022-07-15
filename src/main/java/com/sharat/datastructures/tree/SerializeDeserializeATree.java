/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * SerializeDeserializeATree.java
 *
 * @author S00742735
 * @since 2020-09-28
 */
public class SerializeDeserializeATree {

    // A Binary Tree Node
    static class Node {
        int data;

        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    static class Index {
        int val;

        public Index(int val) {
            this.val = val;
        }
    }

    public List<Integer> serializeTree(Node root) {
        List<Integer> serializedList = new ArrayList<Integer>();
        serialize(root, serializedList);
        return serializedList;
    }

    private void serialize(Node root, List<Integer> serializedList) {
        if (null == root) {
            serializedList.add(-1);
            return;
        }

        serializedList.add(root.data);
        serialize(root.left, serializedList);
        serialize(root.right, serializedList);
    }

    public Node deserializeTree(List<Integer> serializedList) {
        if (null == serializedList || serializedList.isEmpty()) {
            return null;
        }
        Index index = new Index(0);
        Node root = deserialize(serializedList, index);
        return root;
    }

    private Node deserialize(List<Integer> serializedList, Index index) {
        if (index.val == serializedList.size()) {
            return null;
        }

        int val = serializedList.get(index.val);
        index.val++;

        if (val == -1) {
            return null;
        }

        Node root = new Node(val);
        root.left = deserialize(serializedList, index);
        root.right = deserialize(serializedList, index);

        return root;
    }

    public void printPreOrderTree(Node root) {
        if (null == root) {
            return;
        }

        System.out.print(root.data + ", ");
        printPreOrderTree(root.left);
        printPreOrderTree(root.right);
    }

    public static void main(String args[]) {
        // Let us create binary tree shown in above diagram
        Node root = new Node(7);
        root.left = new Node(6);
        root.right = new Node(5);
        root.left.left = new Node(8);
        root.left.left.left = new Node(4);
        root.left.right = new Node(3);
        root.right.left = new Node(2);
        root.right.right = new Node(1);
        /*
         *              7 
         *            /  \ 
         *          6     5 
         *         / \   / \ 
         *        8   3 2   1 
         *       / 
         *      4
         */

        SerializeDeserializeATree serializeDeserializeATree = new SerializeDeserializeATree();
        serializeDeserializeATree.printPreOrderTree(root);
        System.out.println();
        List<Integer> serializedList = serializeDeserializeATree.serializeTree(root);
        System.out.println(serializedList);
        Node deserializedTreeRootNode = serializeDeserializeATree.deserializeTree(serializedList);
        serializeDeserializeATree.printPreOrderTree(deserializedTreeRootNode);
    }

}

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures.tree;

/**
 * ConstructTreeFromInOrderPostOrder.java
 *
 * @author S00742735
 * @since 2020-09-23
 */
public class ConstructTreeFromInOrderPostOrder {
    
    
    static class Node {
        int data;

        Node left, right = null;

        public Node(int data) {
            this.data = data;
        }
    }
    
    static class Index {
        int index;
        public Index(int index) {
            this.index = index;
        }
    }
    
    /**
     * contructTree 
     * @param inOrderArray
     * @param preOrderArray
     * @return Node
     */
    public Node contructTree(int[] inOrderArray, int[] postOrderArray) {
        int startIndex = 0;
        int endIndex = inOrderArray.length - 1;
        Index postOrderIndex = new Index(endIndex);
        return contructTree(inOrderArray, postOrderArray, startIndex, endIndex, postOrderIndex);
    }
    
    public Node contructTree(int[] inOrderArray, int[] postOrderArray, int startIndex, int endIndex, Index postOrderIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        
        Node root = new Node(postOrderArray[postOrderIndex.index]);
        postOrderIndex.index--;
        
        int curInOrderNodeIndex = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (inOrderArray[i] == root.data) {
                curInOrderNodeIndex = i;
                break;
            }
        }
        
        root.right = contructTree(inOrderArray, postOrderArray, curInOrderNodeIndex + 1, endIndex, postOrderIndex);
        root.left = contructTree(inOrderArray, postOrderArray, startIndex, curInOrderNodeIndex - 1, postOrderIndex);
        
        return root;
    }
    
    public void preOrder(Node root) {
        if (null == root) {
            return;
        }
        
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    
    public void inOrder(Node root) {
        if (null == root) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    
    public void postOrder(Node root) {
        if (null == root) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    
    public static void main(String[] args) {
        /*
         *              10
         *             /  \
         *           20    30 
         *          /  \     \
         *         40  50     70
         *                   /  \
         *                  80  90
         * 
         */
        int[] inOrderArray = {40, 20, 50, 10, 30, 80, 70, 90};
        int[] postOrderArray = {40, 50, 20, 80, 90, 70, 30, 10};
        ConstructTreeFromInOrderPostOrder constructTreeFromInOrderPostOrder = new ConstructTreeFromInOrderPostOrder();
        Node root = constructTreeFromInOrderPostOrder.contructTree(inOrderArray, postOrderArray);
        System.out.print("PreOrder : ");
        constructTreeFromInOrderPostOrder.preOrder(root);
        System.out.println();
        System.out.print("InOrder : ");
        constructTreeFromInOrderPostOrder.inOrder(root);
        System.out.println();
        System.out.print("PostOrder : ");
        constructTreeFromInOrderPostOrder.postOrder(root);
        System.out.println();
    }

}

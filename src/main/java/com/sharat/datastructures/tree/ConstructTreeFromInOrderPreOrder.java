/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.sharat.datastructures.tree;

/**
 * ConstructTreeFromInOrderPreOrder.java
 *
 * @author S00742735
 * @since 2020-09-23
 */
// A tree can be constructed if inorder and one of pre-order or post order for a tree is present
public class ConstructTreeFromInOrderPreOrder {

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
    
    public Node contructTree(int[] inOrderArray, int[] preOrderArray) {
        int startIndex = 0;
        int endIndex = inOrderArray.length - 1;
        Index preOrderIndex = new Index(0);
        return contructTree(inOrderArray, preOrderArray, startIndex, endIndex, preOrderIndex);
    }
    
    /**
     * contructTree 
     * @param inOrderArray
     * @param preOrderArray
     * @param startIndex
     * @param endIndex
     * @param preOrderIndex
     * @return Node
     */
    private Node contructTree(int[] inOrderArray, int[] preOrderArray, int startIndex, int endIndex, Index preOrderIndex) {
        
        if (startIndex > endIndex) {
            return null;
        }
        
        Node root = new Node(preOrderArray[preOrderIndex.index]);
        preOrderIndex.index++;
        
        int inOrderNodeIndex = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (inOrderArray[i] == root.data) {
                inOrderNodeIndex = i;
                break;
            }
        }
        
        root.left = contructTree(inOrderArray, preOrderArray, startIndex, inOrderNodeIndex - 1, preOrderIndex);
        root.right = contructTree(inOrderArray, preOrderArray, inOrderNodeIndex + 1, endIndex, preOrderIndex);
        
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
        int[] preOrderArray = {10, 20, 40, 50, 30, 70, 80, 90};
        ConstructTreeFromInOrderPreOrder constructTreeFromInOrderPostOrder = new ConstructTreeFromInOrderPreOrder();
        Node root = constructTreeFromInOrderPostOrder.contructTree(inOrderArray, preOrderArray);
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

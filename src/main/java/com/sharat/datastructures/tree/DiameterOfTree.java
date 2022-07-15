/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures.tree;

/**
 * DiameterOfTree.java
 *
 * @author S00742735
 * @since 2020-09-23
 */
public class DiameterOfTree {

    static class Node {
        int data;

        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
    
    static class Result {
        int res;
        
        public Result(int res) {
            this.res = res;
        }
    }
    
    public int getDiameter(Node root) {
        if (null == root) {
            return 0;
        }
        
        Result result = new Result(0);
        getHeight(root, result);
        return result.res;
    }

    /**
     * getDiameter 
     * @param root void
     */
    public int getHeight(Node root, Result result) {
        if (null == root) {
            return 0;
        }
        
        int lh = getHeight(root.left, result);
        int rh = getHeight(root.right, result);
        result.res = Math.max(result.res, 1 + lh + rh);
        return 1 + Math.max(lh, rh);
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

        DiameterOfTree diameterOfTree = new DiameterOfTree();
        System.out.println(diameterOfTree.getDiameter(root));
    }

}

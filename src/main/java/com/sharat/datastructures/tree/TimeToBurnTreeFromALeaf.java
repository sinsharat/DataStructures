/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.sharat.datastructures.tree;

/**
 * TimeToBurnTreeFromALeaf.java
 *
 * @author S00742735
 * @since 2020-09-28
 */
public class TimeToBurnTreeFromALeaf {

    // A Binary Tree Node
    static class Node {
        int data;

        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
    
    static class Distance {
        int value;

        public Distance(int value) {
            this.value = value;
        }
    }
    
    static class Result {
        int value;

        public Result(int value) {
            this.value = value;
        }
    }
    
    public int calculateTreeBurnTime(Node root, int leaf) {
        Result res = new Result(0);
        Distance dist = new Distance(0);
        calculateBurnTime(root, leaf, dist, res);
        return res.value;
    }
    
    public int calculateBurnTime(Node root, int leaf, Distance dist, Result res) {
        if (null == root) {
            return 0;
        }
        
        // when leaf found set its distance to 0
        if (root.data == leaf) {
            dist.value = 0;
            return 1;
        }
        
        Distance ldist = new Distance(-1);
        Distance rdist = new Distance(-1);
        
        int lh = calculateBurnTime(root.left, leaf, ldist, res);
        int rh = calculateBurnTime(root.right, leaf, ldist, res);
        
        // if ldis != -1 means leaf found in left path, hence for each 
        // subsequent parents from leaf increase the distance by 1
        // also calculate height as distance from leaf of the node + opposite side(i.e. right side) height.
        if (ldist.value != -1) {
            dist.value = ldist.value + 1;
            res.value = Math.max(res.value, dist.value + rh);
        }
        
        // if ldis != -1 means leaf found in right path, hence for each 
        // subsequent parents from leaf increase the distance by 1
        // also calculate height as distance from leaf of the node + opposite side(i.e. left side) height.
        if (rdist.value != -1) {
            dist.value = rdist.value + 1;
            res.value = Math.max(res.value, dist.value + lh);
        }
        
        return 1+ Math.max(lh, rh);
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
         *            /   \ 
         *           6     5 
         *          / \   / \ 
         *         8   3 2   1 
         *        /     
         *       4     
         */

        TimeToBurnTreeFromALeaf timeToBurnTreeFromALeaf = new TimeToBurnTreeFromALeaf();
        System.out.println(timeToBurnTreeFromALeaf.calculateTreeBurnTime(root, 3));
    }
}

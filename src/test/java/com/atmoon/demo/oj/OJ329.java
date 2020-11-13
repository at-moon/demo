package com.atmoon.demo.oj;

import java.util.Arrays;
import java.util.Scanner;

public class OJ329 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int Q = s.nextInt();
        int[] forest = new int[N];
        for (int i = 0; i < N; i++) {
            forest[i] = s.nextInt();
        }
        SegmentTreeNode root = build(forest, 0, N - 1);
        int[] result = new int[Q];
        for (int i = 0; i < Q; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            result[i] = absHeight(root, a - 1, b - 1);
        }
        Arrays.stream(result).forEach(System.out::println);
        s.close();
    }

    private static int absHeight(SegmentTreeNode node, int a, int b) {
        return getHighest(node, a, b) - getShortest(node, a, b);
    }

    private static SegmentTreeNode build(int[] forest, int left, int right) {
        SegmentTreeNode node = new SegmentTreeNode(left, right);
        if (left == right) {
            node.highest = forest[left];
            node.shortest = forest[right];
            return node;
        }
        int mid = (left + right) / 2;
        node.leftNode = build(forest, left, mid);
        node.rightNode = build(forest, mid + 1, right);
        node.highest = Math.max(node.leftNode.highest, node.rightNode.highest);
        node.shortest = Math.min(node.leftNode.shortest, node.rightNode.shortest);
        return node;
    }

    private static int getHighest(SegmentTreeNode node, int left, int right) {
        if (left > node.right || right < node.left) {
            return Integer.MIN_VALUE;
        }
        if (left <= node.left && right >= node.right) {
            return node.highest;
        }
        return Math.max(getHighest(node.leftNode, left, right), getHighest(node.rightNode, left, right));
    }

    private static int getShortest(SegmentTreeNode node, int left, int right) {
        if (left > node.right || right < node.left) {
            return Integer.MAX_VALUE;
        }
        if (left <= node.left && right >= node.right) {
            return node.shortest;
        }
        return Math.min(getShortest(node.leftNode, left, right), getShortest(node.rightNode, left, right));
    }

}

class SegmentTreeNode {
    int left;
    int right;
    int highest;
    int shortest;

    SegmentTreeNode leftNode;
    SegmentTreeNode rightNode;

    public SegmentTreeNode(int left, int right) {
        this.left = left;
        this.right = right;
        leftNode = null;
        rightNode = null;
    }
}
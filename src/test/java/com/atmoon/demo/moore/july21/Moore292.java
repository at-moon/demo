package com.atmoon.demo.moore.july21;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Moore292 {
    public boolean func(RoadNode rn) {
        int[] path = getPath(rn);
        int length = path.length;
        int left = (length - 1) / 2;
        int right = length / 2;
        while (left >= 0 && right < length && path[left] == path[right]) {
            left--;
            right++;
        }
        return right == length;
    }

    private int[] getPath(RoadNode rn) {
        List<Integer> path = new ArrayList<>();
        while (rn != null) {
            path.add(rn.val);
            rn = rn.next;
        }
        return path.stream().mapToInt(val -> val).toArray();
    }

    @Test
    public void test1() {
        RoadNode node = new RoadNode(1);
        RoadNode node1 = new RoadNode(2);
        RoadNode node2 = new RoadNode(3);
        RoadNode node3 = new RoadNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        Assert.assertFalse(func(node));
    }

    @Test
    public void test2() {
        RoadNode node = new RoadNode(1);
        RoadNode node1 = new RoadNode(2);
        RoadNode node2 = new RoadNode(2);
        RoadNode node3 = new RoadNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        Assert.assertTrue(func(node));
    }
}

class RoadNode {
    int val;

    RoadNode next;

    RoadNode(int val) {
        this.val = val;
    }

    RoadNode(int val, RoadNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public RoadNode getNext() {
        return next;
    }
}
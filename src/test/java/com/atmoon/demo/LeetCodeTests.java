package com.atmoon.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zy
 */
public class LeetCodeTests {

    @Test
    public void test20() {
        Assert.assertTrue(LeetCode.isValid("()"));
        Assert.assertTrue(LeetCode.isValid("()[]{}"));
        Assert.assertFalse(LeetCode.isValid("(]"));
        Assert.assertFalse(LeetCode.isValid("([)]"));
        Assert.assertTrue(LeetCode.isValid("{[]}"));
    }

    @Test
    public void test34() {
        Assert.assertArrayEquals(new int[]{3, 4}, LeetCode.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        Assert.assertArrayEquals(new int[]{-1, -1}, LeetCode.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        Assert.assertArrayEquals(new int[]{-1, -1}, LeetCode.searchRange(new int[]{}, 6));
        Assert.assertArrayEquals(new int[]{0, 0}, LeetCode.searchRange(new int[]{5}, 5));
        Assert.assertArrayEquals(new int[]{0, 3}, LeetCode.searchRange(new int[]{5, 5, 5, 5}, 5));
    }

    @Test
    public void test35() {
        Assert.assertEquals(2, LeetCode.searchInsert(new int[]{1, 3, 5, 6}, 5));
        Assert.assertEquals(1, LeetCode.searchInsert(new int[]{1, 3, 5, 6}, 2));
        Assert.assertEquals(4, LeetCode.searchInsert(new int[]{1, 3, 5, 6}, 7));
        Assert.assertEquals(0, LeetCode.searchInsert(new int[]{1, 3, 5, 6}, 0));
        Assert.assertEquals(1, LeetCode.searchInsert(new int[]{1, 3}, 2));
        Assert.assertEquals(1, LeetCode.searchInsert(new int[]{1}, 2));
        Assert.assertEquals(0, LeetCode.searchInsert(new int[]{}, 2));
    }

    @Test
    public void test36() {
        Assert.assertEquals(true, LeetCode.isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        Assert.assertEquals(false, LeetCode.isValidSudoku(new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
    }

    @Test
    public void test37() {
        LeetCode leetCode = new LeetCode();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        leetCode.solveSudoku(board);
        System.out.println(board);
    }

    @Test
    public void test38() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals("1", leetCode.countAndSay(1));
        Assert.assertEquals("11", leetCode.countAndSay(2));
        Assert.assertEquals("21", leetCode.countAndSay(3));
        Assert.assertEquals("1211", leetCode.countAndSay(4));
        Assert.assertEquals("111221", leetCode.countAndSay(5));
    }

    @Test
    public void test39() {
        LeetCode leetCode = new LeetCode();
        int[] candidates = new int[]{9, 2, 3, 6, 7};
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(2);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(7);
        res.add(tmp);
        Assert.assertEquals(res, leetCode.combinationSum(candidates, 7));

        candidates = new int[]{2, 3, 5};
        res = new ArrayList<>();
        tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(2);
        tmp.add(2);
        tmp.add(2);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(3);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(5);
        res.add(tmp);
        Assert.assertEquals(res, leetCode.combinationSum(candidates, 8));
    }

    @Test
    public void test43() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals("0", leetCode.multiply("0", "0"));
        Assert.assertEquals("6", leetCode.multiply("2", "3"));
        Assert.assertEquals("208", leetCode.multiply("13", "16"));
        Assert.assertEquals("56088", leetCode.multiply("123", "456"));
    }

    @Test
    public void test46() {
        LeetCode leetCode = new LeetCode();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(3);
        tmp.add(2);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(1);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(3);
        tmp.add(1);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(1);
        tmp.add(2);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(2);
        tmp.add(1);
        res.add(tmp);
        Assert.assertEquals(res, leetCode.permute(new int[]{1, 2, 3}));
        res.remove(res.size() - 1);
        res.remove(res.size() - 1);
        tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(2);
        tmp.add(1);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(1);
        tmp.add(2);
        res.add(tmp);
        Assert.assertEquals(res, leetCode.permute2(new int[]{1, 2, 3}));
    }

    @Test
    public void test53() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(6, leetCode.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        Assert.assertEquals(-1, leetCode.maxSubArray(new int[]{-1}));
        Assert.assertEquals(1, leetCode.maxSubArray(new int[]{-2, 1}));
        Assert.assertEquals(6, leetCode.maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        Assert.assertEquals(-1, leetCode.maxSubArray2(new int[]{-1}));
        Assert.assertEquals(1, leetCode.maxSubArray2(new int[]{-2, 1}));
    }

    @Test
    public void test54() {
        LeetCode leetCode = new LeetCode();
        List<Integer> actual = leetCode.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        List<Integer> expected = Arrays.stream(new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5}).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        actual = leetCode.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        expected = Arrays.stream(new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        actual = leetCode.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});
        expected = Arrays.stream(new int[]{1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8}).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        actual = leetCode.spiralOrder(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}});
        expected = Arrays.stream(new int[]{1, 2, 4, 6, 8, 7, 5, 3}).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        actual = leetCode.spiralOrder(new int[][]{});
        expected = Arrays.stream(new int[0]).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        actual = leetCode.spiralOrder(new int[][]{{7}, {9}, {6}});
        expected = Arrays.stream(new int[]{7, 9, 6}).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test59() {
        LeetCode leetCode = new LeetCode();
        int[][] actual = leetCode.generateMatrix(3);
        int[][] expected = new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(Arrays.stream(expected[i]).boxed().collect(Collectors.toList()), Arrays.stream(actual[i]).boxed().collect(Collectors.toList()));
        }
        actual = leetCode.generateMatrix(2);
        expected = new int[][]{{1, 2}, {4, 3}};
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(Arrays.stream(expected[i]).boxed().collect(Collectors.toList()), Arrays.stream(actual[i]).boxed().collect(Collectors.toList()));
        }
        actual = leetCode.generateMatrix(1);
        expected = new int[][]{{1}};
        for (int i = 0; i < 1; i++) {
            Assert.assertEquals(Arrays.stream(expected[i]).boxed().collect(Collectors.toList()), Arrays.stream(actual[i]).boxed().collect(Collectors.toList()));
        }
    }

    @Test
    public void test62() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(3, leetCode.uniquePaths(3, 2));
        Assert.assertEquals(28, leetCode.uniquePaths(7, 3));
        Assert.assertEquals(1916797311, leetCode.uniquePaths(51, 9));
        Assert.assertEquals(3, leetCode.uniquePaths2(3, 2));
        Assert.assertEquals(28, leetCode.uniquePaths2(7, 3));
        Assert.assertEquals(1916797311, leetCode.uniquePaths2(51, 9));
    }

    @Test
    public void test70() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(5, leetCode.climbStairs(4));
        Assert.assertEquals(8, leetCode.climbStairs(5));
    }

    @Test
    public void test78() {
        LeetCode leetCode = new LeetCode();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(1);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(2);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(2);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(3);
        res.add(tmp);
        tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        res.add(tmp);
        Assert.assertEquals(res, leetCode.subsets(new int[]{1, 2, 3}));
    }

    @Test
    public void test88() {
        LeetCode leetCode = new LeetCode();
        List<Integer> expected = Arrays.stream(new int[]{1, 2, 2, 3, 5, 6}).boxed().collect(Collectors.toList());
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        leetCode.merge(nums1, 3, nums2, 3);
        List<Integer> actual = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        nums1 = new int[]{1, 2, 3, 0, 0, 0};
        leetCode.merge2(nums1, 3, nums2, 3);
        actual = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        expected = Arrays.stream(new int[]{1}).boxed().collect(Collectors.toList());
        nums1 = new int[]{0};
        nums2 = new int[]{1};
        leetCode.merge2(nums1, 0, nums2, 1);
        actual = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test104() {
        LeetCode leetCode = new LeetCode();
        LeetCode.TreeNode node = new LeetCode.TreeNode(3);
        LeetCode.TreeNode node1 = new LeetCode.TreeNode(9);
        LeetCode.TreeNode node2 = new LeetCode.TreeNode(20);
        LeetCode.TreeNode node3 = new LeetCode.TreeNode(15);
        LeetCode.TreeNode node4 = new LeetCode.TreeNode(7);
        node.left = node1;
        node.right = node2;
        node2.left = node3;
        node2.right = node4;
        Assert.assertEquals(3, leetCode.maxDepth(node));
    }

    @Test
    public void test121() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(5, leetCode.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(0, leetCode.maxProfit(new int[]{7, 6, 4, 3, 1}));
        Assert.assertEquals(1, leetCode.maxProfit(new int[]{1, 2}));
        Assert.assertEquals(5, leetCode.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(0, leetCode.maxProfit2(new int[]{7, 6, 4, 3, 1}));
        Assert.assertEquals(1, leetCode.maxProfit2(new int[]{1, 2}));
    }

    @Test
    public void test122() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(7, leetCode.maxProfitSecond(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(4, leetCode.maxProfitSecond(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(0, leetCode.maxProfitSecond(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void test124() {
        LeetCode leetCode = new LeetCode();
        LeetCode.TreeNode node = new LeetCode.TreeNode(-10);
        LeetCode.TreeNode node1 = new LeetCode.TreeNode(9);
        LeetCode.TreeNode node2 = new LeetCode.TreeNode(20);
        LeetCode.TreeNode node3 = new LeetCode.TreeNode(15);
        LeetCode.TreeNode node4 = new LeetCode.TreeNode(7);
        node.left = node1;
        node.right = node2;
        node2.left = node3;
        node2.right = node4;
        Assert.assertEquals(42, leetCode.maxPathSum(node));
        node = new LeetCode.TreeNode(-3);
        Assert.assertEquals(-3, leetCode.maxPathSum(node));
    }

    @Test
    public void test136() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(1, leetCode.singleNumber(new int[]{2, 2, 1}));
        Assert.assertEquals(4, leetCode.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    @Test
    public void test141() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(3);
        LeetCode.ListNode node1 = new LeetCode.ListNode(2);
        LeetCode.ListNode node2 = new LeetCode.ListNode(0);
        LeetCode.ListNode node3 = new LeetCode.ListNode(-4);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        Assert.assertEquals(true, leetCode.hasCycle(node));
        Assert.assertEquals(true, leetCode.hasCycle2(node));
    }

    @Test
    public void test142() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(3);
        LeetCode.ListNode node1 = new LeetCode.ListNode(2);
        LeetCode.ListNode node2 = new LeetCode.ListNode(0);
        LeetCode.ListNode node3 = new LeetCode.ListNode(-4);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        Assert.assertEquals(node1, leetCode.detectCycle(node));
        Assert.assertEquals(node1, leetCode.detectCycle2(node));
        node = new LeetCode.ListNode(1);
        node.next = node1;
        node1.next = node;
        Assert.assertEquals(node, leetCode.detectCycle(node));
        Assert.assertEquals(node, leetCode.detectCycle2(node));
        node = new LeetCode.ListNode(1);
        Assert.assertNull(leetCode.detectCycle(node));
        Assert.assertNull(leetCode.detectCycle2(node));
    }

    @Test
    public void test146() {
        LeetCode.LRUCache cache = new LeetCode.LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));
        // 该操作会使得密钥 2 作废
        cache.put(3, 3);
        Assert.assertEquals(-1, cache.get(2));
        // 该操作会使得密钥 1 作废
        cache.put(4, 4);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(3));
        Assert.assertEquals(4, cache.get(4));

        cache = new LeetCode.LRUCache(2);
        Assert.assertEquals(-1, cache.get(2));
        cache.put(2, 6);
        Assert.assertEquals(-1, cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        Assert.assertEquals(2, cache.get(1));
        Assert.assertEquals(6, cache.get(2));

        LeetCode.LRUCache2 cache2 = new LeetCode.LRUCache2(2);
        cache2.put(1, 1);
        cache2.put(2, 2);
        Assert.assertEquals(1, cache2.get(1));
        // 该操作会使得密钥 2 作废
        cache2.put(3, 3);
        Assert.assertEquals(-1, cache2.get(2));
        // 该操作会使得密钥 1 作废
        cache2.put(4, 4);
        Assert.assertEquals(-1, cache2.get(1));
        Assert.assertEquals(3, cache2.get(3));
        Assert.assertEquals(4, cache2.get(4));

        cache2 = new LeetCode.LRUCache2(2);
        Assert.assertEquals(-1, cache2.get(2));
        cache2.put(2, 6);
        Assert.assertEquals(-1, cache2.get(1));
        cache2.put(1, 5);
        cache2.put(1, 2);
        Assert.assertEquals(2, cache2.get(1));
        Assert.assertEquals(6, cache2.get(2));
    }

    @Test
    public void test148() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(4);
        LeetCode.ListNode node1 = new LeetCode.ListNode(2);
        LeetCode.ListNode node2 = new LeetCode.ListNode(1);
        LeetCode.ListNode node3 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node = leetCode.sortList(node);
        Assert.assertEquals(1, node.val);
        node = node.next;
        Assert.assertEquals(2, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(4, node.val);

        node = new LeetCode.ListNode(-1);
        node1 = new LeetCode.ListNode(5);
        node2 = new LeetCode.ListNode(3);
        node3 = new LeetCode.ListNode(4);
        LeetCode.ListNode node4 = new LeetCode.ListNode(0);
        LeetCode.ListNode node5 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node = leetCode.sortList(node);
        Assert.assertEquals(-1, node.val);
        node = node.next;
        Assert.assertEquals(0, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(4, node.val);
        node = node.next;
        Assert.assertEquals(5, node.val);
    }

    @Test
    public void test148v2() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(4);
        LeetCode.ListNode node1 = new LeetCode.ListNode(2);
        LeetCode.ListNode node2 = new LeetCode.ListNode(1);
        LeetCode.ListNode node3 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node = leetCode.sortList2(node);
        Assert.assertEquals(1, node.val);
        node = node.next;
        Assert.assertEquals(2, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(4, node.val);

        node = new LeetCode.ListNode(-1);
        node1 = new LeetCode.ListNode(5);
        node2 = new LeetCode.ListNode(3);
        node3 = new LeetCode.ListNode(4);
        LeetCode.ListNode node4 = new LeetCode.ListNode(0);
        LeetCode.ListNode node5 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node = leetCode.sortList2(node);
        Assert.assertEquals(-1, node.val);
        node = node.next;
        Assert.assertEquals(0, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(3, node.val);
        node = node.next;
        Assert.assertEquals(4, node.val);
        node = node.next;
        Assert.assertEquals(5, node.val);
    }

    @Test
    public void test155() {
        LeetCode.MinStack minStack = new LeetCode.MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    @Test
    public void test160() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(4);
        LeetCode.ListNode node1 = new LeetCode.ListNode(1);
        LeetCode.ListNode node2 = new LeetCode.ListNode(8);
        LeetCode.ListNode node3 = new LeetCode.ListNode(4);
        LeetCode.ListNode node4 = new LeetCode.ListNode(5);
        LeetCode.ListNode node5 = new LeetCode.ListNode(5);
        LeetCode.ListNode node6 = new LeetCode.ListNode(0);
        LeetCode.ListNode node7 = new LeetCode.ListNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node5.next = node6;
        node6.next = node7;
        node7.next = node2;
        Assert.assertEquals(node2, leetCode.getIntersectionNode(node, node5));
        Assert.assertEquals(node2, leetCode.getIntersectionNode2(node, node5));

        node = new LeetCode.ListNode(0);
        node1 = new LeetCode.ListNode(9);
        node2 = new LeetCode.ListNode(1);
        node3 = new LeetCode.ListNode(2);
        node4 = new LeetCode.ListNode(4);
        node5 = new LeetCode.ListNode(3);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node5.next = node3;
        Assert.assertEquals(node3, leetCode.getIntersectionNode(node, node5));
        Assert.assertEquals(node3, leetCode.getIntersectionNode2(node, node5));

        node = new LeetCode.ListNode(2);
        node1 = new LeetCode.ListNode(6);
        node2 = new LeetCode.ListNode(4);
        node3 = new LeetCode.ListNode(1);
        node4 = new LeetCode.ListNode(5);
        node.next = node1;
        node1.next = node2;
        node3.next = node4;
        Assert.assertNull(leetCode.getIntersectionNode(node, node3));
        Assert.assertNull(leetCode.getIntersectionNode2(node, node3));

        node = new LeetCode.ListNode(1);
        node1 = new LeetCode.ListNode(2);
        node2 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;
        Assert.assertEquals(node, leetCode.getIntersectionNode(node, node));
        Assert.assertEquals(node, leetCode.getIntersectionNode2(node, node));
    }

    @Test
    public void test169() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(3, leetCode.majorityElement(new int[]{3, 2, 3}));
        Assert.assertEquals(2, leetCode.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
        Assert.assertEquals(3, leetCode.majorityElement2(new int[]{3, 2, 3}));
        Assert.assertEquals(2, leetCode.majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    @Test
    public void test206() {
        LeetCode leetCode = new LeetCode();
        LeetCode.ListNode node = new LeetCode.ListNode(1);
        LeetCode.ListNode node1 = new LeetCode.ListNode(2);
        LeetCode.ListNode node2 = new LeetCode.ListNode(3);
        node.next = node1;
        node1.next = node2;

        node = leetCode.reverseList(node);
        Assert.assertEquals(3, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(1, node.next.next.val);

        node = new LeetCode.ListNode(1);
        node1 = new LeetCode.ListNode(2);
        node2 = new LeetCode.ListNode(3);

        node.next = node1;
        node1.next = node2;

        node = leetCode.reverseList2(node);
        Assert.assertEquals(3, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(1, node.next.next.val);
    }

    @Test
    public void test215() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(5, leetCode.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assert.assertEquals(4, leetCode.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        Assert.assertEquals(2, leetCode.findKthLargest(new int[]{2, 1}, 1));
        Assert.assertEquals(5, leetCode.findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assert.assertEquals(4, leetCode.findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        Assert.assertEquals(2, leetCode.findKthLargest2(new int[]{2, 1}, 1));
        Assert.assertEquals(5, leetCode.findKthLargest3(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assert.assertEquals(4, leetCode.findKthLargest3(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        Assert.assertEquals(2, leetCode.findKthLargest3(new int[]{2, 1}, 1));
    }

    @Test
    public void test217() {
        LeetCode leetCode = new LeetCode();
        Assert.assertTrue(leetCode.containsDuplicate(new int[]{1, 2, 3, 1}));
        Assert.assertFalse(leetCode.containsDuplicate(new int[]{1, 2, 3, 4}));
        Assert.assertTrue(leetCode.containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    @Test
    public void test230() {
        LeetCode leetCode = new LeetCode();
        LeetCode.TreeNode node = new LeetCode.TreeNode(3);
        LeetCode.TreeNode node1 = new LeetCode.TreeNode(1);
        LeetCode.TreeNode node2 = new LeetCode.TreeNode(4);
        LeetCode.TreeNode node3 = new LeetCode.TreeNode(2);
        node.left = node1;
        node.right = node2;
        node1.right = node3;
        Assert.assertEquals(1, leetCode.kthSmallest(node, 1));
        Assert.assertEquals(1, leetCode.kthSmallest2(node, 1));
    }

    @Test
    public void test231() {
        LeetCode leetCode = new LeetCode();
        Assert.assertFalse(leetCode.isPowerOfTwo(-2));
        Assert.assertFalse(leetCode.isPowerOfTwo(-1));
        Assert.assertFalse(leetCode.isPowerOfTwo(0));
        Assert.assertTrue(leetCode.isPowerOfTwo(1));
        Assert.assertTrue(leetCode.isPowerOfTwo(2));
        Assert.assertFalse(leetCode.isPowerOfTwo(3));
        Assert.assertFalse(leetCode.isPowerOfTwo2(-2));
        Assert.assertFalse(leetCode.isPowerOfTwo2(-1));
        Assert.assertFalse(leetCode.isPowerOfTwo2(0));
        Assert.assertTrue(leetCode.isPowerOfTwo2(1));
        Assert.assertTrue(leetCode.isPowerOfTwo2(2));
        Assert.assertFalse(leetCode.isPowerOfTwo2(3));
    }

    @Test
    public void test235() {
        LeetCode leetCode = new LeetCode();
        LeetCode.TreeNode node = new LeetCode.TreeNode(6);
        LeetCode.TreeNode node1 = new LeetCode.TreeNode(2);
        LeetCode.TreeNode node2 = new LeetCode.TreeNode(8);
        LeetCode.TreeNode node3 = new LeetCode.TreeNode(0);
        LeetCode.TreeNode node4 = new LeetCode.TreeNode(4);
        LeetCode.TreeNode node5 = new LeetCode.TreeNode(7);
        LeetCode.TreeNode node6 = new LeetCode.TreeNode(9);
        LeetCode.TreeNode node7 = new LeetCode.TreeNode(3);
        LeetCode.TreeNode node8 = new LeetCode.TreeNode(5);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        Assert.assertEquals(node, leetCode.lowestCommonAncestor(node, node1, node2));
        Assert.assertEquals(node1, leetCode.lowestCommonAncestor(node, node1, node4));
    }

    @Test
    public void test238() {
        LeetCode leetCode = new LeetCode();
        int[] nums = new int[]{1, 2, 3, 4};
        int[] expect = new int[]{24, 12, 8, 6};
        int[] result = leetCode.productExceptSelf(nums);
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(expect[i], result[i]);
        }
        nums = new int[]{0, 0};
        expect = new int[]{0, 0};
        result = leetCode.productExceptSelf(nums);
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(expect[i], result[i]);
        }
        nums = new int[]{1, 2, 3, 4};
        expect = new int[]{24, 12, 8, 6};
        result = leetCode.productExceptSelf2(nums);
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(expect[i], result[i]);
        }
        nums = new int[]{0, 0};
        expect = new int[]{0, 0};
        result = leetCode.productExceptSelf2(nums);
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(expect[i], result[i]);
        }

    }

    @Test
    public void test292() {
        LeetCode leetCode = new LeetCode();
        Assert.assertFalse(leetCode.canWinNim(4));
        Assert.assertTrue(leetCode.canWinNim(5));
    }

    @Test
    public void test344() {
        LeetCode leetCode = new LeetCode();
        char[] before = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] expect = new char[]{'o', 'l', 'l', 'e', 'h'};
        leetCode.reverseString(before);
        for (int i = 0; i < before.length; i++) {
            Assert.assertEquals(before[i], expect[i]);
        }
        before = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        expect = new char[]{'h', 'a', 'n', 'n', 'a', 'H'};
        leetCode.reverseString(before);
        for (int i = 0; i < before.length; i++) {
            Assert.assertEquals(before[i], expect[i]);
        }
        before = new char[]{'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c', 'a', 'n', 'a', 'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a'};
        expect = new char[]{'a', 'm', 'a', 'n', 'a', 'P', ' ', ':', 'l', 'a', 'n', 'a', 'c', ' ', 'a', ' ', ',', 'n', 'a', 'l', 'p', ' ', 'a', ' ', ',', 'n', 'a', 'm', ' ', 'A'};
        leetCode.reverseString(before);
        for (int i = 0; i < before.length; i++) {
            Assert.assertEquals(before[i], expect[i]);
        }
    }

    @Test
    public void test557() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc", leetCode.reverseWords("Let's take LeetCode contest"));
    }

    @Test
    public void test236() {
        LeetCode leetCode = new LeetCode();
        LeetCode.TreeNode node = new LeetCode.TreeNode(3);
        LeetCode.TreeNode node1 = new LeetCode.TreeNode(5);
        LeetCode.TreeNode node2 = new LeetCode.TreeNode(1);
        LeetCode.TreeNode node3 = new LeetCode.TreeNode(6);
        LeetCode.TreeNode node4 = new LeetCode.TreeNode(2);
        LeetCode.TreeNode node5 = new LeetCode.TreeNode(0);
        LeetCode.TreeNode node6 = new LeetCode.TreeNode(8);
        LeetCode.TreeNode node7 = new LeetCode.TreeNode(7);
        LeetCode.TreeNode node8 = new LeetCode.TreeNode(4);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        Assert.assertEquals(node, leetCode.lowestCommonAncestor2(node, node1, node2));
        Assert.assertEquals(node1, leetCode.lowestCommonAncestor2(node, node1, node8));
    }

    @Test
    public void testOffer45() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals("102", leetCode.minNumber(new int[]{10, 2}));
        Assert.assertEquals("3033459", leetCode.minNumber(new int[]{3, 30, 34, 5, 9}));
        Assert.assertEquals("030359", leetCode.minNumber(new int[]{3, 30, 0, 5, 9}));
    }

    @Test
    public void testQuicksort() {
        LeetCode leetCode = new LeetCode();
        int[] array = new int[]{1, 45, 4, 4, 6, 0};
        leetCode.quicksort(array, 0, array.length - 1);
        Assert.assertArrayEquals(new int[]{0, 1, 4, 4, 6, 45}, array);
    }

    @Test
    public void testOffer03() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(2, leetCode.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    @Test
    public void testOffer49() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals(1, leetCode.nthUglyNumber(1));
        Assert.assertEquals(12, leetCode.nthUglyNumber(10));
        Assert.assertEquals(51200000, leetCode.nthUglyNumber(1000));
    }

    @Test
    public void test130() {
        LeetCode leetCode = new LeetCode();
        char[][] result = new char[][]{
                {'X', 'X', 'X', 'O'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        leetCode.solveBfs(result);
        char[][] expected = new char[][]{
                {'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        Assert.assertArrayEquals(expected, result);
        result = new char[][]{
                {'O', 'O'},
                {'O', 'O'}
        };
        leetCode.solveBfs(result);
        expected = new char[][]{
                {'O', 'O'},
                {'O', 'O'}
        };
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testSimpleArrayList() {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.remove("b");
        Assert.assertEquals(1, arrayList.indexOf("c"));
    }

    @Test
    public void testCountOffInACircle() {
        LeetCode leetCode = new LeetCode();
        Assert.assertArrayEquals(new Object[]{2, 4, 6, 3, 1, 5}, leetCode.countOffInACircle(6));
    }

    @Test
    public void testOffer02() {
        LeetCode leetCode = new LeetCode();
        Assert.assertTrue(leetCode.findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5));
        Assert.assertTrue(leetCode.findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 30));
        Assert.assertFalse(leetCode.findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 100));
    }

    @Test
    public void testOffer05() {
        LeetCode leetCode = new LeetCode();
        Assert.assertEquals("We%20are%20happy.", leetCode.replaceSpace("We are happy."));
    }
}

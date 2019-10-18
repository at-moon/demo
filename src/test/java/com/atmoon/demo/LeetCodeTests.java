package com.atmoon.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zy
 */
public class LeetCodeTests {

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

        candidates = new int[] {2, 3, 5};
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
}

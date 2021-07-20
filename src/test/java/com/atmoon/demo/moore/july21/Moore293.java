/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.moore.july21;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Moore293 {

    public boolean isContainsNearNum(int[] nums, int m, int n) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long celling = set.ceiling((long) nums[i] - n);
            if (celling != null && celling <= (long) nums[i] + n) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= m) {
                set.remove((long) nums[i - m]);
            }
        }
        return false;
    }

    public boolean isContainsNearNum2(int[] nums, int m, int n) {
        int length = nums.length;
        Map<Long, Long> bucket = new HashMap<>();
        for (int i = 0; i < length; i++) {
            long id = getBucketId(nums[i], n + 1);
            if (bucket.containsKey(id)) {
                return true;
            }
            if (bucket.get(id - 1) != null && Math.abs(bucket.get(id - 1) - nums[i]) <= n) {
                return true;
            }
            if (bucket.get(id + 1) != null && Math.abs(bucket.get(id + 1) - nums[i]) <= n) {
                return true;
            }
            bucket.put(id, (long) nums[i]);
            // 只对下标差不超过m的元素进行判断
            if (i >= m) {
                bucket.remove(getBucketId(nums[i - m], n + 1));
            }
        }
        return false;
    }

    private long getBucketId(long val, long size) {
        if (val >= 0) {
            return val / size;
        }
        return val / size - 1;
    }

    @Test
    public void test1() {
        Assert.assertTrue(isContainsNearNum(new int[] {1, 2, 3, 1}, 3, 0));
        Assert.assertTrue(isContainsNearNum(new int[] {1, 0, 1, 1}, 1, 2));
        Assert.assertFalse(isContainsNearNum(new int[] {1, 5, 9, 1, 5, 9}, 2, 3));
        Assert.assertTrue(isContainsNearNum(new int[] {1, 3, 5, 1}, 3, 0));
        Assert.assertFalse(isContainsNearNum(new int[] {2, 7, 15, 2, 7}, 2, 2));
    }

    @Test
    public void test2() {
        Assert.assertTrue(isContainsNearNum2(new int[] {1, 2, 3, 1}, 3, 0));
        Assert.assertTrue(isContainsNearNum2(new int[] {1, 0, 1, 1}, 1, 2));
        Assert.assertFalse(isContainsNearNum2(new int[] {1, 5, 9, 1, 5, 9}, 2, 3));
        Assert.assertTrue(isContainsNearNum2(new int[] {1, 3, 5, 1}, 3, 0));
        Assert.assertFalse(isContainsNearNum2(new int[] {2, 7, 15, 2, 7}, 2, 2));
    }
}

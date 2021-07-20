/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.moore.july21;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Moore232 {

    public List<String> findRepeatedSequences(String s) {
        Set<String> subSet = new HashSet<>();
        Set<String> repeated = new TreeSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i + 10);
            if (subSet.contains(subStr)) {
                repeated.add(subStr);
            }
            subSet.add(subStr);
        }
        return repeated.isEmpty() ? null : new ArrayList<>(repeated);
    }

    @Test
    public void test1() {
        List<String> repeated = findRepeatedSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        Assert.assertArrayEquals(new String[] {"AAAAACCCCC", "CCCCCAAAAA"}, repeated.toArray());
    }

    @Test
    public void test2() {
        List<String> repeated = findRepeatedSequences("AAAAAAAAAAAAA");
        Assert.assertArrayEquals(new String[] {"AAAAAAAAAA"}, repeated.toArray());
    }

    @Test
    public void test3() {
        List<String> repeated = findRepeatedSequences("AAAAACCCCCGGGGGTTTTT");
        Assert.assertNull(repeated);
    }
}

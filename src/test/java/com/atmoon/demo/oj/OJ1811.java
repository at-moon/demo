/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.oj;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OJ1811 {

    private static final int ARRAY_SIZE = 10000;

    private static int unlock(String initState, String dstState) {
        boolean[] prime = getPrimeArray(ARRAY_SIZE);
        Queue<String> queue = new LinkedList<>();
        queue.offer(initState);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String stateStr = queue.poll();
                if (dstState.equals(stateStr)) {
                    return step;
                }
                if (stateStr == null) {
                    continue;
                }
                int state = Integer.parseInt(stateStr);
                // 标记经过的数
                prime[state] = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j <= 9; j++) {
                        String next = new StringBuilder(stateStr).replace(i, i + 1, j + "").toString();
                        if (prime[Integer.parseInt(next)]) {
                            queue.offer(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private static boolean[] getPrimeArray(int size) {
        boolean[] prime = new boolean[size + 1];
        Arrays.fill(prime, true);
        // 1不是质数
        prime[1] = false;
        for (int i = 2; i < Math.sqrt(size); i++) {
            for (int j = i + 1; j <= size; j++) {
                if (j % i == 0) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    @Test
    public void getPrimeArrayTest() {
        boolean[] prime = getPrimeArray(10);
        List<Integer> primeList = new ArrayList<>();
        for (int i = 1; i < prime.length; i++) {
            if (prime[i]) {
                primeList.add(i);
            }
        }
        Assert.assertEquals(primeList, Arrays.asList(2, 3, 5, 7));
    }

    @Test
    public void unlockTest1() {
        Assert.assertEquals(2, unlock("0023", "0059"));
    }

    @Test
    public void unlockTest2() {
        Assert.assertEquals(5, unlock("1373", "8017"));
    }

    @Test
    public void name() {
        Assert.assertEquals(-1, unlock("0005", "11111"));
    }
}

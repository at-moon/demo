/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.oj;

import org.junit.Assert;
import org.junit.Test;

public class OJ1814 {

    // 找到所有的连续递增段
    static int getMinStep(int[] steps) {
        int preStep = 0;
        int minStep = 0;
        for (int step : steps) {
            if (step > preStep) {
                minStep += step - preStep;
            }
            preStep = step;
        }
        return minStep;
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, getMinStep(new int[] {1, 2, 3, 2, 1}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(6, getMinStep(new int[] {0, 1, 2, 0, 2, 4, 2, 1, 0}));
    }

}

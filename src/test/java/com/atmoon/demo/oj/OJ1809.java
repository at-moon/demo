/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class OJ1809 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int pvErrorTolerance = Integer.parseInt(cin.nextLine());
        int[] pvByHourWeekly = new int[7 * 24];
        for (int i = 0; i < pvByHourWeekly.length; i++) {
            pvByHourWeekly[i] = cin.nextInt();
        }
        cin.close();
        int[] results = getBestTimeWindow(pvByHourWeekly, pvErrorTolerance);
        String[] strResult = Arrays.stream(results).mapToObj(String::valueOf).toArray(String[]::new);
        System.out.println(String.join(" ", strResult));
    }

    private static int[] getBestTimeWindow(int[] pvByHourWeekly, int pvErrorTolerance) {
        int[] window = new int[] {-1, -1};
        int length = pvByHourWeekly.length;
        long sum = pvByHourWeekly[0];
        int currentLength = 0;
        int maxLength = 0;
        int i = 0;
        int j = 0;
        while (i < length) {
            do {
                j = (j + 1) == length ? 0 : j + 1;
                sum += pvByHourWeekly[j];
                currentLength++;
            } while (currentLength < length && sum <= pvErrorTolerance);
            if (currentLength > maxLength && sum - pvByHourWeekly[j] <= pvErrorTolerance) {
                window[0] = i;
                window[1] = j - 1 >= 0 ? j - 1 : j + length - 1;

                maxLength = currentLength;
            }
            do {
                sum -= pvByHourWeekly[i];
                i++;
                currentLength--;
            } while (i < length && sum > pvErrorTolerance);
        }
        return window;
    }

}

package com.atmoon.demo.oj;

import java.util.Scanner;

public class OJ397 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        int x = s.nextInt();
        int[] nums = new int[c];
        for (int i = 0; i < c; i++) {
            nums[i] = s.nextInt();
        }
        long[] sum = new long[c + 1];
        long temp = 0;
        // sum[0] = 0,sum[1] - sum[0] 表示包含nums[0]的长度为1的区间和
        for (int i = 0; i < c; i++) {
            temp += nums[i];
            sum[i + 1] = temp;
        }
        System.out.println(countRecursive(sum, x, 0, c));
        s.close();
    }

    private static long countRecursive(long[] sum, int x, int left, int right) {
        // 区间长度为0
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        // 首先计算左右两部分区间个数
        long count = countRecursive(sum, x, left, mid) + countRecursive(sum, x, mid + 1, right);
        // 计算l在左侧部分，r在右侧部分区间个数
        int l = left;
        int r = mid + 1;
        while (l <= mid) {
            while (r <= right && sum[r] - sum[l] < x) {
                r++;
            }
            count += right - r + 1;
            l++;
        }
        // 合并两侧有序数组
        long[] sorted = new long[right - left + 1];
        int p = 0;
        int p1 = left, p2 = mid + 1;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) {
                sorted[p++] = sum[p2++];
            } else if (p2 > right) {
                sorted[p++] = sum[p1++];
            } else {
                if (sum[p1] > sum[p2]) {
                    sorted[p++] = sum[p2++];
                } else {
                    sorted[p++] = sum[p1++];
                }
            }
        }
        if (sorted.length >= 0) {
            System.arraycopy(sorted, 0, sum, left, sorted.length);
        }
        return count;
    }
}

package com.atmoon.demo.oj;

import java.util.Scanner;

public class OJ225 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (power(i, k, n) % n == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static long power(long base, int times, int n) {
        long result = 1;
        // a * b % k = (a % k) * (b % k) % k
        while (times > 0) {
            if (times % 2 == 1) {
                times = times - 1;
                result = result * base % n;
            }
            times = times / 2;
            base = base * base % n;
        }
        return result;
    }

}

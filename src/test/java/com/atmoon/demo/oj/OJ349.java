package com.atmoon.demo.oj;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OJ349 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println(find(n));
    }

    private static int find(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = 2; j <= 30; j++) {
                long temp = power(i, j);
                if (temp > n) {
                    break;
                }
                set.add((int) temp);
            }
        }
        return set.size();
    }

    private static long power(int a, int b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
}

package com.atmoon.demo.oj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OJ299 {

    private static final Map<Character, Character> map;

    static {
        map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine());
        int[] results = new int[T];
        for (int i = 0; i < T; i++) {
            results[i] = minAdd(in.nextLine());
        }
        for (int result : results) {
            System.out.println(result);
        }
    }

    private static int minAdd(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        // l为每次判断的子串长度
        for (int l = 0; l < length; l++) {
            for (int i = 0; i + l < length; i++) {
                if (l == 0) {
                    // 单个括号需要补一个
                    dp[i][i] = 1;
                } else if (l == 1) {
                    // 两个括号判断是否匹配
                    dp[i][i + 1] = match(s, i, i + 1) ? 0 : 2;
                } else {
                    dp[i][i + l] = Integer.MAX_VALUE;
                    // 长度大于2时先判断两端是否匹配
                    if (match(s, i, i + l)) {
                        dp[i][i + l] = dp[i + 1][i + l - 1];
                    }
                    // 将子串分为两段,取和最小值
                    for (int j = 0; j < l; j++) {
                        dp[i][i + l] = Math.min(dp[i][i + l], dp[i][i + j] + dp[i + j + 1][i + l]);
                    }
                }
            }
        }
        return dp[0][length - 1];
    }

    private static boolean match(String s, int l, int r) {
        return map.containsKey(s.charAt(l)) && s.charAt(r) == map.get(s.charAt(l));
    }

}

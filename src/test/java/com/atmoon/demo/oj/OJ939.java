package com.atmoon.demo.oj;

import java.util.*;

public class OJ939 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int B = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        int[] A = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> result = findPath(A, B);
        if (result.size() == 0) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i : result) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static List<Integer> findPath(int[] A, int B) {
        List<Integer> result = new ArrayList<>();
        int length = A.length;
        // 从i开始跳到终点的最小花费
        int[] dp = new int[length];
        // 使用-1标记还未到达过
        Arrays.fill(dp, -1);
        // 终点花费为0
        dp[length - 1] = 0;
        Map<Integer, Integer> path = new HashMap<>();
        // 从右往左
        for (int i = length - 2; i >= 0; i--) {
            if (A[i] == -1) {
                continue;
            }
            // 遍历以i为起点所有能到达的位置
            // 从最左位置开始尝试,这样在有重复结果时能保证字典序最小
            for (int j = 1; j <= B && i + j < length; j++) {
                // 不能继续跳或无法到达终点
                if (A[i + j] == -1 || dp[i + j] == -1) {
                    continue;
                }
                // 第一次到达或者找到更小花费
                if (dp[i] == -1 || dp[i] > dp[i + j] + A[i]) {
                    dp[i] = dp[i + j] + A[i];
                    path.put(i, i + j);
                }
            }
        }
        if (dp[0] != -1) {
            int i = 0;
            result.add(i + 1);
            while (i != length - 1) {
                result.add(path.get(i) + 1);
                i = path.get(i);
            }
        }
        return result;
    }

}

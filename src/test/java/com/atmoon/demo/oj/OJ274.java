package com.atmoon.demo.oj;

import java.util.Arrays;
import java.util.Scanner;

public class OJ274 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = Integer.parseInt(s.nextLine());
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            int[] param = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = param[0], C = param[1], Q = param[2];
            int[][] operates = new int[Q][2];
            for (int j = 0; j < Q; j++) {
                operates[j] = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            result[i] = switchBall(C, operates);
        }
        Arrays.stream(result).forEach(System.out::println);
    }

    private static int switchBall(int C, int[][] operates) {
        for (int[] operate : operates) {
            int l = operate[0], r = operate[1];
            if (C >= l && C <= r) {
                C = l + r - C;
            }
        }
        return C;
    }
}

package com.atmoon.demo.oj;

import java.util.Scanner;

public class OJ258 {

    static int[] leftMod;
    static int[] rightMod;
    static int length;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        length = s.length();
        int a = in.nextInt();
        int b = in.nextInt();
        modLeft(s, a);
        modRight(s, b);
        checkExistsResult(s);
    }

    private static void modLeft(String s, int a) {
        leftMod = new int[length];
        leftMod[0] = (s.charAt(0) - '0') % a;
        for (int i = 1; i < length; i++) {
            leftMod[i] = (leftMod[i - 1] * 10 + (s.charAt(i) - '0')) % a;
        }
    }

    private static void modRight(String s, int b) {
        rightMod = new int[length];
        rightMod[length - 1] = (s.charAt(length - 1) - '0') % b;
        // 记录10/100/1000... % b
        int[] temp = new int[length];
        temp[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            temp[i] = temp[i + 1] * 10 % b;
            rightMod[i] = (temp[i] * (s.charAt(i) - '0') + rightMod[i + 1]) % b;
        }
    }

    private static void checkExistsResult(String s) {
        int i = 0;
        for (; i < length - 1; i++) {
            if (s.charAt(0) == '0' && i > 0) {
                continue;
            }
            if (i < length - 2 && s.charAt(i + 1) == '0') {
                continue;
            }
            if (leftMod[i] == 0 && rightMod[i + 1] == 0) {
                System.out.println("YES");
                System.out.println(s.substring(0, i + 1));
                System.out.println(s.substring(i + 1));
                break;
            }
        }
        if (i == length - 1) {
            System.out.println("NO");
        }
    }
}

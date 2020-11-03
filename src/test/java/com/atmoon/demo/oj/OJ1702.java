package com.atmoon.demo.oj;

import java.util.*;

public class OJ1702 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(checkPalindromeFormation(a, b));
    }

    private static boolean checkPalindromeFormation(String a, String b) {
        int left = a.length() / 2 - 1;
        left = Math.min(check(a, a, left), check(b, b, left));
        left = Math.min(check(a, b, left), check(b, a, left));
        return left == -1;
    }

    private static int check(String a, String b, int left) {
        int right = a.length() - 1 - left;
        while (left >= 0 && right <= a.length() - 1) {
            if (a.charAt(left) != b.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return left;
    }

}

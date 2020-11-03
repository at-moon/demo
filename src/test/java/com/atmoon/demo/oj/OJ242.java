package com.atmoon.demo.oj;

import java.util.Scanner;

public class OJ242 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String p = in.next();
        int minRemove = Integer.MAX_VALUE;
        for (int i = 0; i <= s.length() - p.length(); i++) {
            minRemove = Math.min(minRemove, removeCharacter(s.substring(i), p));
        }
        System.out.println(minRemove);
    }

    private static int removeCharacter(String s, String p) {
        int i = 0, j = 0;
        int removeNum = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                removeNum++;
            }
        }
        return j == p.length() ? removeNum : Integer.MAX_VALUE;
    }


}

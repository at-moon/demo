package com.atmoon.demo.oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OJ47 {

    private static List<String> result;
    private static List<Integer> temp;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int n = s.nextInt(), m = s.nextInt();
            temp = new ArrayList<>();
            result = new ArrayList<>();
            combination(n, m, 1);
            result.forEach(System.out::println);
        }
    }

    private static void combination(int n, int m, int index) {
        if (temp.size() == m) {
            StringBuilder sb = new StringBuilder();
            temp.forEach(a -> sb.append(a).append(" "));
            result.add(sb.toString().trim());
            return;
        }
        if (index <= n) {
            temp.add(index);
            combination(n, m, index + 1);
            temp.remove(temp.size() - 1);
            combination(n, m, index + 1);
        }
    }

}

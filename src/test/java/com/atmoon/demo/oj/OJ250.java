package com.atmoon.demo.oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OJ250 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        List<Integer> result = getDaffodilNumber(N);
        result.forEach(System.out::println);
        System.out.println(result.size());
    }

    private static List<Integer> getDaffodilNumber(int N) {
        List<Integer> daffodil = new ArrayList<>();
        for (int i = power(10, N - 1); i < power(10, N); i++) {
            int sum = 0, temp = i;
            while (temp > 0) {
                sum += power(temp % 10, N);
                temp = temp / 10;
            }
            if (sum == i) {
                daffodil.add(i);
            }
        }
        return daffodil;
    }

    private static int power(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
}

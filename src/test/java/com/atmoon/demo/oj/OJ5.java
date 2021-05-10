package com.atmoon.demo.oj;

import java.util.Scanner;

public class OJ5 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            double m = s.nextDouble();
            int n = s.nextInt();
            if (m == 0 && n == 0) {
                break;
            }
            cal(m, n);
        }
    }

    private static void cal(double m, int n) {
        StringBuilder sb = new StringBuilder("0.");
        while (sb.length() < 12) {
            m *= n;
            int temp = (int) m;
            sb.append(temp);
            m = m - temp;
        }
        System.out.println(sb);
    }

}

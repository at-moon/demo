package com.atmoon.demo.oj;

import java.util.*;

public class OJ11 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<MedalInfo> medalInfos = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            MedalInfo info = new MedalInfo();
            info.country = s.next();
            info.gold = s.nextInt();
            info.silver = s.nextInt();
            info.bronze = s.nextInt();
            medalInfos.add(info);
        }
        medalInfos.sort((a, b) -> {
            if (a.gold != b.gold) {
                return b.gold - a.gold;
            }
            if (a.silver != b.silver) {
                return b.silver - a.silver;
            }
            if (a.bronze != b.bronze) {
                return b.bronze - a.bronze;
            }
            return a.country.compareTo(b.country);
        });
        for (MedalInfo info : medalInfos) {
            System.out.println(info.country);
        }
    }

    static class MedalInfo {
        String country;
        int gold;
        int silver;
        int bronze;
    }
}

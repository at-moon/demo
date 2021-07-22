/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package com.atmoon.demo.oj;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OJ1810 {

    private static final String KEY_WORD_PASSWORD = "PASSWORD";

    private static final String KEY_WORD_PWD = "PWD";

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        cin.nextLine();
        String[] keys = cin.nextLine().split(" ");
        String log = cin.nextLine();
        cin.close();
        String result = logAnonymize(keys, log);
        System.out.println(result);
    }

    private static String logAnonymize(String[] keys, String log) {
        List<String> upperKeys = Arrays.stream(keys).map(String::toUpperCase).collect(Collectors.toList());
        String[] entryArray = log.split(",");
        StringBuilder sb = new StringBuilder();
        for (String entryStr : entryArray) {
            String[] entry = entryStr.split(":");
            String key = entry[0];
            String value = entry[1];
            value = anonymize(key, value, upperKeys);
            sb.append(key).append(":").append(value).append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static String anonymize(String key, String value, List<String> upperKeys) {
        String upperKey = key.toUpperCase(Locale.ENGLISH);
        if (KEY_WORD_PASSWORD.equals(upperKey) || KEY_WORD_PWD.equals(upperKey)) {
            return "******";
        }
        if (upperKeys.contains(upperKey) && upperKey.endsWith("IP")) {
            String[] ipArray = value.split("\\.");
            return ipArray[0] + ".***.***." + ipArray[3];
        }
        if (upperKeys.contains(upperKey)) {
            for (int i = value.length() - 1; i >= 0; i--) {
                int count = 0;
                while (i >= 0 && (value.charAt(i) >= '0' && value.charAt(i) <= '9')) {
                    count++;
                    i--;
                }
                if (count >= 4) {
                    int endIndex = i + 1 + count - count / 4;
                    int startIndex = endIndex - count / 2;
                    StringBuilder replaceStr = new StringBuilder();
                    for (int j = 0; j < count / 2; j++) {
                        replaceStr.append("*");
                    }
                    StringBuilder temp = new StringBuilder(value);
                    temp.replace(startIndex, endIndex, replaceStr.toString());
                    return temp.toString();
                }
            }
        }
        return value;
    }

    @Test
    public void test1() {
        String log
            = "Apn:cmnet,Qos:121212121212,CellID:4600175319,Imsi:460019852146201,GWIP:1.1.1.1,UserIp:2.2.2.2,CID:854710-336985-852,UID:1-2-3-4,pwd:huawei7410";
        Assert.assertEquals(
            "Apn:cmnet,Qos:121212121212,CellID:460*****19,Imsi:46001*******201,GWIP:1.1.1.1,UserIp:2.***.***.2,CID:854710-33***5-852,UID:1-2-3-4,pwd:******",
            logAnonymize(new String[] {"IMSI", "CellID", "UserIP", "CID", "UID"}, log));

    }
}

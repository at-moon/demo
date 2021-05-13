package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class OJ1790 {

    private static final int MAC_LENGTH = 17;

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String macAddress = cin.nextLine();
        cin.close();
        int result = getMacNum(macAddress);
        System.out.println(result);
    }

    // 待实现函数，在此函数中填入答题代码
    private static int getMacNum(String macAddress) {
        Set<String> validMac = new HashSet<>();
        // 将字符改为小写，规避重复
        macAddress = macAddress.toLowerCase(Locale.ENGLISH);
        // 记录上一分隔符
        Character lastSeparator = null;
        // 截取长度为12 + 5,长度不足时直接结束
        for (int i = 0, j = 0; i <= macAddress.length() - MAC_LENGTH; ) {
            j = Math.max(j, i);
            for (; j < i + MAC_LENGTH; j++) {
                char temp = macAddress.charAt(j);
                if ((j - i) % 3 != 2) {
                    if (isValid(temp)) {
                        continue;
                    }
                    break;
                }
                // 分隔符要一致
                if (!isSeparator(temp) || (lastSeparator != null && temp != lastSeparator)) {
                    break;
                }
                lastSeparator = temp;
            }
            if (j == i + MAC_LENGTH) {
                // 对于合法数据进行转义，-\:转为' '
                validMac.add(macAddress.substring(i, j).replace('-', ' ').replace(':', ' '));
                // 对于合法数据只用检查后续三位是否合法
                i = i + 3;
            } else {
                i++;
                if (lastSeparator != null) {
                    // 分隔符不一致时从上一分隔符最后出现处开始
                    // 01:02:03:02-03-04-05-06-07
                    i = Math.max(i, macAddress.lastIndexOf(lastSeparator, j) + 1);
                }
                // 清空lastSeparator
                lastSeparator = null;
            }
        }
        return validMac.size();
    }

    private static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isLowerCaseLetter(char c) {
        return c >= 'a' && c <= 'f';
    }

    private static boolean isValid(char ch) {
        return isNum(ch) || isLowerCaseLetter(ch);
    }

    private static boolean isSeparator(char c) {
        return c == ':' || c == '-';
    }

}

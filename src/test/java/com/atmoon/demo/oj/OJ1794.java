package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class OJ1794 {

    private static String VOWEL = "aeiouAEIOU";

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int flaw = Integer.parseInt(cin.nextLine());
        String input = cin.nextLine();
        cin.close();

        System.out.println(getLongestFlawedVowelSubstrLen(flaw, input));
    }

    private static int getLongestFlawedVowelSubstrLen(int flaw, String input) {
        int maxLength = 0;
        int currentFlaw = 0;
        int left = 0;
        int right = 0;
        char[] inputArray = input.toCharArray();
        while (right < input.length()) {
            if (!isVowel(inputArray[right])) {
                currentFlaw++;
            }
            // 缺陷度过大时左窗右移
            while (currentFlaw > flaw) {
                if (!isVowel(inputArray[left++])) {
                    currentFlaw--;
                }
            }
            if (left < input.length() && isVowel(inputArray[left]) && isVowel(inputArray[right])
                && currentFlaw == flaw) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
        }
        return maxLength;
    }

    private static boolean isVowel(char ch) {
        return VOWEL.contains(String.valueOf(ch));
    }

}

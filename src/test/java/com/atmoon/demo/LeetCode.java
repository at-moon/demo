package com.atmoon.demo;

import java.util.*;

/**
 * @Author: zy
 */
public class LeetCode {


    public static void main(String[] args) {
        System.out.println(isMatch("aaa", "ab*a*c*a"));
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {//nums[i] != nums[i-1] 去重
                int l = i + 1, r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;//去重
                        l++;
                        r--;
                    } else if (sum > 0) {
                        while (l < r && nums[r] == nums[r - 1]) r--;//去重
                        r--;
                    } else {
                        while (l < r && nums[l] == nums[l + 1]) l++;//去重
                        l++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        // 最小数大于0/最大数小于0时无解
        if (len > 0 && nums[0] <= 0 && nums[len - 1] >= 0) {
            for (int i = 0; i < len - 2; ) {
                if (nums[i] > 0) {
                    break;
                }
                // 定义左右边界
                int l = i + 1, r = len - 1;
                while (l < r) {
                    // 如果最大值小于0跳出循环
                    if (nums[r] < 0) {
                        break;
                    }
                    int result = nums[i] + nums[l] + nums[r];
                    if (result == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        // 如果匹配成功将左边界右移
                        while (l < r && nums[l] == nums[++l]) {
                        }
                    } else if (result < 0) {
                        // 结果偏小最小值右移，考虑去重
                        while (l < r && nums[l] == nums[++l]) {
                        }
                    } else {
                        // 结果偏大最大值左移，考虑去重
                        while (l < r && nums[r] == nums[--r]) {
                        }
                    }
                }
                // 如果下一位与当前位相等则跳过
                while (nums[i] == nums[++i] && i < len - 2) {
                }
            }
        }
        return res;
    }

    /**
     * 16. 最接近的三数之和
     *
     * @param nums
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; ) {
            int l = i + 1, r = len - 1;
            while (l < r) {
                int temp = nums[i] + nums[l] + nums[r];
                if (temp == target) {
                    return temp;
                } else if (temp > target) {
                    // 如果和比目标大则右边界左移,相同数直接跳过
                    while (l < r && nums[r] == nums[--r]) {
                    }
                } else {
                    // 如果和比目标小则左边界右移,相同数直接跳过
                    while (l < r && nums[l] == nums[++l]) {
                    }
                }
                if (Math.abs(sum - target) > Math.abs(temp - target)) {
                    sum = temp;
                }
            }
            // 下个数相同直接跳过
            while (i < len - 2 && nums[i] == nums[++i]) {
            }
        }
        return sum;
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int maxHeight = 0;
        int maxHeightIndex = 0;
        // 先找到最后一个最高高度坐标
        for (int i = 0; i < len; i++) {
            if (height[i] >= maxHeight) {
                maxHeight = height[i];
                maxHeightIndex = i;
            }
        }
        // 上一个高度
        int lastHeight = 0;
        // 容量
        int trap = 0;
        // 左右边界
        int l = 0, r = len - 1;
        int temp = 0;
        while (l <= maxHeightIndex) {
            if (height[l] < lastHeight) {
                temp += lastHeight - height[l];
            } else {
                lastHeight = height[l];
                trap += temp;
                temp = 0;
            }
            l++;
        }
        // 从右侧再统计一遍
        lastHeight = 0;
        while (r >= maxHeightIndex) {
            if (height[r] < lastHeight) {
                temp += lastHeight - height[r];
            } else {
                lastHeight = height[r];
                trap += temp;
                temp = 0;
            }
            r--;
        }
        return trap;
    }

    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode currentNode = null;
        // 进位数
        int carryDigit = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val, v2 = l2 == null ? 0 : l2.val;
            // 获取当前位两数之和+进位数
            int sum = v1 + v2 + carryDigit;
            int temp = sum % 10;
            carryDigit = sum / 10;
            if (node == null) {
                currentNode = node = new ListNode(temp);
            } else {
                currentNode.next = new ListNode(temp);
                currentNode = currentNode.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        // 循环结束后进位数仍有值则存入下一位
        if (carryDigit != 0) {
            currentNode.next = new ListNode(carryDigit);
        }
        return node;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring0(String s) {
        int maxLength = s.length() < 1 ? 0 : 1;
        int length = s.length();
        for (int i = 0; i < length - maxLength; i++) {
            int current = i + 1;
            // 如果下一位不包含在当前子串中则继续
            while (current < length && !s.substring(i, current).contains(String.valueOf(s.charAt(current)))) {
                current++;
            }
            maxLength = Math.max(maxLength, current - i);
        }
        return maxLength;
    }

    /**
     * 3. 无重复字符的最长子串
     * 优化的滑动窗口(发现重复字符后直接从第一次出现重复字符的下一位开始)
     * abcdbef省去了从b开始的一次循环
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), maxLength = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            maxLength = Math.max(maxLength, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return maxLength;
    }

    /**
     * 4. 寻找两个有序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 保证 m <= n
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            // i 需要增大
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            }
            // i 需要减小
            else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            }
            // 达到要求，并且将边界条件列出来单独考虑
            else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // 奇数的话返回左侧最大值
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                // 如果是偶数的话加上右侧最小值计算平均值
                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /**
     * 5. 最长回文子串
     * 暴力法(将所有可能的字符串都判断一遍)
     *
     * @param s
     * @return
     */
    public static String longestPalindromeViolent(String s) {
        int length = s.length();
        String palindrome = "";
        for (int i = 0; i < length - palindrome.length(); i++) {
            for (int j = i + palindrome.length(); j < length; j++) {
                int l = i, r = j;
                while (l < r) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l++;
                        r--;
                    } else {
                        break;
                    }
                }
                // 长度为奇数/偶数
                if (l == r || l == r + 1 && palindrome.length() < j + 1 - i) {
                    palindrome = s.substring(i, j + 1);
                }
            }
        }
        return palindrome;
    }

    /**
     * 5. 最长回文子串
     * 中心扩展法(边界会有两个、中心只有一个)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int beginIndex = 0, endIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > endIndex - beginIndex + 1) {
                beginIndex = i - (len - 1) / 2;
                endIndex = i + len / 2;
            }
        }
        return s.substring(beginIndex, endIndex + 1);
    }

    private static int expandCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        // 最后一次循环扩大了边界(r - 1) - (l + 1) + 1
        return r - l - 1;
    }

    /**
     * 6. Z 字形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        StringBuilder[] lineStrArray = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            lineStrArray[i] = new StringBuilder();
        }
        int lineIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            // 每次回到第一行作为一次循环的开始
            int loopIndex = i % (2 * numRows - 2);
            lineStrArray[lineIndex].append(s.charAt(i));
            if (loopIndex < numRows - 1) {
                lineIndex++;
            } else {
                lineIndex--;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : lineStrArray) {
            result.append(sb);
        }
        return result.toString();
    }

    /**
     * 7. 整数反转
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            // 反转后可能溢出返回0
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            rev = rev * 10 + pop;
            x /= 10;
        }
        return rev;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        String trim = str.trim();
        if (trim.length() == 0 || (!isNumber(trim.charAt(0)) && trim.charAt(0) != '+' && trim.charAt(0) != '-')) {
            return 0;
        }
        // 截取有效数字范围
        // parseInt会转换符号位,begin仅用来计算有效长度
        int begin = 0, end = 0;
        // 标识正负数
        boolean sign = true;
        if (trim.charAt(0) == '+') {
            begin = end = 1;
        } else if (trim.charAt(0) == '-') {
            begin = end = 1;
            sign = false;
        }
        // 找到第一个非零数
        while (begin < trim.length() && trim.charAt(begin) == '0') {
            begin++;
        }
        // 找到最后一位数字
        while (end < trim.length() && isNumber(trim.charAt(end))) {
            end++;
        }
        if (end == begin) {
            return 0;
        }
        // 计算边界值长度
        int maxLen = (int) Math.ceil(Math.log10(Integer.MAX_VALUE));
        // 有效位数超长直接返回边界值
        if (end - begin > maxLen) {
            if (sign) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (end - begin == maxLen) {
            int temp = Integer.parseInt(trim.substring(0, end - 1));
            int lastBit = Integer.parseInt(trim.substring(end - 1, end));
            if (temp > Integer.MAX_VALUE / 10 || (temp == Integer.MAX_VALUE / 10 && lastBit > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (temp < Integer.MIN_VALUE / 10 || (temp == Integer.MIN_VALUE / 10 && -lastBit < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
        }
        return Integer.parseInt(trim.substring(0, end));
    }

    private static boolean isNumber(char bit) {
        return bit >= '0' && bit <= '9';
    }

    /**
     * 9. 回文数
     * 反转一半数字
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        // 如果将数字全部反转可能会存在整数溢出,只反转一半并与另一半比较
        int rev = 0;
        while (x > rev) {
            rev = x % 10 + rev * 10;
            x = x / 10;
        }
        return x == rev || x == rev / 10;
    }

    /**
     * 10. 正则表达式匹配
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        // 均为空时返回ture
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        // s不为空、p为空时返回false
        if (p.length() == 0) {
            return false;
        }
        // 均不为空、s为空p不为空时进行校验
        int sIndex = 0, pIndex = 0;
        char current = 0;
        while (sIndex < s.length() && pIndex < p.length()) {
            if (p.charAt(pIndex) == '*') {
                // 如果*前一位为空返回false
                if (current == 0) {
                    return false;
                }
                while (sIndex < s.length() && (s.charAt(sIndex) == current || current == '.')) {
                    sIndex++;
                    // 如果匹配符下一位与当前匹配字符相同则顺延,防止'a*a'这种
                    if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == current) {
                        pIndex++;
                    }
                }
            } else {
                current = p.charAt(pIndex);
                // 下一位通配符为*时可以匹配零个
                if (!(current == '.' || s.charAt(sIndex) == current) && pIndex + 1 < p.length() && p.charAt(pIndex + 1) != '*') {
                    return false;
                }
                // 匹配成功(下一位为*时不移动字符坐标)
                if ((current == '.' || s.charAt(sIndex) == current) && (pIndex + 1 == p.length() || p.charAt(pIndex + 1) != '*')) {
                    sIndex++;
                }
            }
            // 避免'a*a'越界
            if (pIndex < p.length()) {
                pIndex++;
            }
        }
        // 如果匹配符未到末位则进行判断,避免'a*b*v*c*x*'之类的情况
        int endP = pIndex;
        while (pIndex < p.length()) {
            // 剩余匹配符为偶数但偶数位不是*
            if ((pIndex - endP) % 2 == 1 && p.charAt(pIndex) != '*') {
                return false;
            }
            // 剩余匹配符为奇数
            if ((pIndex - endP) % 2 == 0 && pIndex == p.length() - 1) {
                return false;
            }
            pIndex++;
        }
        return sIndex == s.length();
    }

}

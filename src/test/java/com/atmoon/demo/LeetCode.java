package com.atmoon.demo;

import java.util.*;

/**
 * @Author: zy
 */
public class LeetCode {


    public static void main(String[] args) {

        nextPermutation(new int[]{1, 2, 3});
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

    static class ListNode {
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
     * 10. 正则表达式匹配(回溯法)
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        // .可以匹配任意字符
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        // *匹配零个或者多个
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 10. 正则表达式匹配(动态规划)
     * 类似于回溯法,将匹配结果存入数组避免字符串的重复生成
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchDynamicPlanning(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length() &&
                        (p.charAt(j) == s.charAt(i) ||
                                p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    /**
     * 12. 整数转罗马数字
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        int[] arab = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] roman = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        StringBuilder sb = new StringBuilder();
        for (int i = arab.length - 1; i >= 0; i--) {
            while (num >= arab[i]) {
                sb.append(roman[i]);
                num -= arab[i];
            }
        }

        return sb.toString();
    }

    /**
     * 13. 罗马数字转整数
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int[] arab = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] roman = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        StringBuilder sb = new StringBuilder(s);
        int i = roman.length - 1, result = 0;
        while (sb.length() > 0) {
            if (sb.indexOf(roman[i]) == 0) {
                sb.delete(0, roman[i].length());
                result += arab[i];
            } else {
                i--;
            }
        }

        return result;
    }


    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char current = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            current = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // 找到长度最小串或不相等
                if (strs[j].length() == i || strs[j].charAt(i) != current) {
                    return sb.toString();
                }
            }
            sb.append(current);
        }
        return sb.toString();
    }

    /**
     * 208. 实现 Trie (前缀树)
     */
    static class Trie {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char current = word.charAt(i);
                if (!node.containsKey(current)) {
                    node.put(current, new TrieNode());
                }
                node = node.get(current);
            }
            node.setEnd();
        }

        /**
         * Returns if the word is in the trie.
         */
        boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }

        /**
         * search a prefix or whole key in trie and
         * returns the node where search ends
         *
         * @param word
         * @return
         */
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char current = word.charAt(i);
                if (node.containsKey(current)) {
                    node = node.get(current);
                } else {
                    return null;
                }
            }
            return node;
        }
    }

    static class TrieNode {

        // 保存当前节点的所有子节点
        private TrieNode[] links;

        // 子节点可能出现总数
        private final int SIZE = 26;

        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[SIZE];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        String[][] keyLetterMap = {{"!", "@", "#"}, {"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        combinations = combination(keyLetterMap[digits.charAt(0) - '1'], new String[]{});
        for (int i = 1; i < digits.length(); i++) {
            combinations = combination(combinations.toArray(new String[combinations.size()]), keyLetterMap[digits.charAt(i) - '1']);
        }
        return combinations;
    }

    /**
     * 对两个数组进行结合
     *
     * @param a
     * @param b
     * @return
     */
    private static List<String> combination(String[] a, String[] b) {
        List<String> combinations = new ArrayList<>();
        for (String l1 : a) {
            if (b.length == 0) {
                combinations.add(l1);
            }
            for (String l2 : b) {
                combinations.add(l1 + l2);
            }
        }
        return combinations;
    }


    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 排除无结果情况([[-5,-4,-3,1]] -11 不能将target与0的情况混为一谈)
        if (len < 4) {
            return result;
        }
        for (int i = 0; i < len - 3; i++) {
            // 如果当前位与上一位相等则跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                // 如果当前位与上一位相等则跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) {
                    continue;
                }
                int base = nums[i] + nums[j];
                int l = j + 1, r = len - 1;
                while (l < r) {
                    int sum = base + nums[l] + nums[r];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        // 如果匹配成功将左边界右移
                        while (l < r && nums[l] == nums[++l]) {

                        }
                    } else if (sum < target) {
                        // 结果偏小最小值右移，考虑去重
                        while (l < r && nums[l] == nums[++l]) {

                        }
                    } else {
                        // 结果偏大最大值左移，考虑去重
                        while (l < r && nums[r] == nums[--r]) {

                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        HashMap<Integer, ListNode> map = new HashMap();
        int i = 0;
        ListNode node = head;
        while (node != null) {
            map.put(i, node);
            node = node.next;
            i++;
        }
        // 如果移除的是第一位
        if (i == n) {
            return head.next;
        }
        map.get(i - n - 1).next = map.get(i - n + 1);
        return head;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 官方题解(一次循环)
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndOfficial(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            switch (current) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty() || current != stack.pop()) {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null) {
            while (l2 != null && l2.val <= l1.val) {
                temp.next = l2;
                l2 = l2.next;
                temp = temp.next;
            }
            temp.next = l1;
            l1 = l1.next;
            temp = temp.next;
        }
        while (l2 != null) {
            temp.next = l2;
            l2 = l2.next;
            temp = temp.next;
        }
        return result.next;
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis(c))
                    for (String right : generateParenthesis(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }


    /**
     * 23. 合并K个排序链表
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        while (lists.length > 1) {
            ListNode[] temp = new ListNode[(lists.length + 1) / 2];
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l2 = null;
                if (i + 1 < lists.length) {
                    l2 = lists[i + 1];
                }
                temp[i / 2] = mergeTwoLists(lists[i], l2);
            }
            lists = temp;
        }
        return lists.length == 0 ? null : lists[0];
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (head != null) {
            if (head.next != null) {
                temp.next = head.next;
                head.next = head.next.next;
                temp.next.next = head;
                temp = temp.next;
            } else {
                temp.next = head;
            }
            head = head.next;
            temp = temp.next;
        }
        return result.next;
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        ListNode[] nodes = new ListNode[k];
        boolean isEnd = false;
        while (!isEnd) {
            ListNode current = head;
            // 将当前需要翻转的k位存入数组,如果位数不够更新标识
            for (int i = 0; i < k; i++) {
                if (current != null) {
                    nodes[i] = current;
                    current = current.next;
                } else {
                    isEnd = true;
                }
            }
            // 需要翻转
            if (!isEnd) {
                // 依次更新存入的节点，最后一位即翻转之后的首位
                nodes[0].next = nodes[k - 1].next;
                for (int i = 1; i < k; i++) {
                    nodes[i].next = nodes[i - 1];
                }
                temp.next = nodes[k - 1];
                // 移到当前位
                head = current;
                for (int i = 0; i < k; i++) {
                    temp = temp.next;
                }
            } else {
                temp.next = head;
            }
        }
        return result.next;
    }

    /**
     * 26. 删除排序数组中的重复项
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int current = nums[0];
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (current == nums[i]) {
                continue;
            } else {
                current = nums[i];
                nums[++j] = current;
            }
        }
        return j + 1;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 27. 移除元素(因为可以不考虑排序,所以直接进行元素替换)
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 1;
                for (; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                }
                if (j == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 29. 两数相除
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        // 最小数翻转会导致溢出
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            } else if (divisor == 1) {
                return Integer.MIN_VALUE;
            } else if (divisor == -2) {
                return -(Integer.MIN_VALUE >> 1);
            } else if (divisor == 2) {
                return Integer.MIN_VALUE >> 1;
            }
            dividend += 1;
        }
        if (dividend == 0) {
            return 0;
        }
        // 标识符号
        boolean isPositive = true;
        // 获取符号标识以及翻转负数
        if (dividend < 0) {
            dividend = -dividend;
            if (divisor > 0) {
                isPositive = false;
            } else {
                divisor = -divisor;
            }
        } else if (divisor < 0) {
            isPositive = false;
            divisor = -divisor;
        }
        int result = 0, temp = dividend;
        for (int i = 31; i >= 0; i--) {
            // 将除数左移可能溢出,通过被除数右移来判断
            if ((temp >> i) >= divisor) {
                temp -= divisor << i;
                result += 1 << i;
            }
        }
        return isPositive ? result : -result;
    }


    /**
     * 30. 串联所有单词的子串
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if (s.length() == 0 || words.length == 0) {
            return new ArrayList<>();
        }
        int len = words[0].length();


        return null;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i > 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i - 1]) {
                j--;
            }
            swap(nums, i - 1, j);
        }
        reverse(nums, i);
    }

    /**
     * 翻转部分数组(指定坐标到结尾)
     *
     * @param nums
     * @param startIndex
     */
    private static void reverse(int[] nums, int startIndex) {
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            swap(nums, startIndex, endIndex);
            startIndex++;
            endIndex--;
        }
    }

    /**
     * 交换数组两位
     *
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }


}

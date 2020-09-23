package com.atmoon.demo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zy
 */
public class LeetCode {

    public static void main(String[] args) {
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

        ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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
    public List<String> letterCombinations(String digits) {
        String[][] keyLetterMap = {{"!", "@", "#"}, {"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        combinations = combination(keyLetterMap[digits.charAt(0) - '1'], new String[]{});
        for (int i = 1; i < digits.length(); i++) {
            combinations = combination(combinations.toArray(new String[0]), keyLetterMap[digits.charAt(i) - '1']);
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
    private List<String> combination(String[] a, String[] b) {
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

    /**
     * 32. 最长有效括号
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses1(String s) {
        int maxLength = 0, start = 0;
        while (s.length() - start > maxLength) {
            Stack<Character> stack = new Stack<>();
            for (int i = start; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current == '(') {
                    stack.push(')');
                } else if (stack.isEmpty() || current != stack.pop()) {
                    break;
                }
                if (stack.isEmpty()) {
                    maxLength = Math.max(maxLength, i - start + 1);
                }
            }
            start++;
        }
        return maxLength;
    }

    /**
     * 32. 最长有效括号(存入左括号下标)
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }
        int pivot = findPivotOfRotateSortArray(nums);
        return findTargetInRotateSortArray(nums, target, pivot);
    }

    private static int findPivotOfRotateSortArray(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            return 0;
        }
        int pivot = 0, l = 0, r = nums.length - 1;
        while (l <= r) {
            pivot = (l + r) / 2;
            if (nums[pivot] > nums[pivot + 1])
                break;
            else {
                if (nums[pivot] < nums[l])
                    r = pivot - 1;
                else
                    l = pivot + 1;
            }
        }
        return pivot + 1;
    }

    private static int findTargetInRotateSortArray(int[] nums, int target, int pivot) {
        int length = nums.length, l = 0, r = nums.length - 1;
        if (pivot == 0) {
            if (target > nums[length - 1] || target < nums[0]) {
                return -1;
            }
        } else {
            if (target > nums[pivot - 1] || target < nums[pivot]) {
                return -1;
            }
            if (target < nums[0]) {
                l = pivot;
            } else {
                r = pivot;
            }
        }
        while (l <= r) {
            int index = (l + r) / 2;
            if (nums[index] == target) {
                return index;
            } else {
                if (nums[index] > target) {
                    r = index - 1;
                } else {
                    l = index + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int start = -1;
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == nums.length || nums[l] != target) {
            return targetRange;
        }
        targetRange[0] = l;
        // 获取右边界
        r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        targetRange[1] = l - 1;
        return targetRange;
    }

    /**
     * 35. 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int boxIndex = (i / 3) * 3 + j / 3;

                if (rows[i].contains(c) || cols[j].contains(c) || boxes[boxIndex].contains(c)) {
                    return false;
                }
                rows[i].add(c);
                cols[j].add(c);
                boxes[boxIndex].add(c);
            }
        }
        return true;
    }

    HashSet<Character>[] rows = new HashSet[9];
    HashSet<Character>[] cols = new HashSet[9];
    HashSet<Character>[] boxes = new HashSet[9];

    private char[][] board;

    boolean sudokuSolved = false;

    int N = 9;

    /**
     * 37. 解数独
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int boxIndex = (i / 3) * 3 + j / 3;

                rows[i].add(c);
                cols[j].add(c);
                boxes[boxIndex].add(c);
            }
        }
        backtrack(0, 0);
    }

    private void backtrack(int row, int col) {
        if (board[row][col] == '.') {
            for (int d = 1; d < 10; d++) {
                char num = (char) ('0' + d);
                // 无有效数字时不会继续放置
                if (couldPlace(row, col, num)) {
                    placeNumber(row, col, num);
                    placeNextNumbers(row, col);
                    // 下一位无法继续放置时移除当前位,尝试当前位下一个有效数字
                    if (!sudokuSolved) {
                        removeNumber(row, col, num);
                    }
                }
            }
        } else {
            placeNextNumbers(row, col);
        }
    }

    private boolean couldPlace(int row, int col, char num) {
        int boxIndex = (row / 3) * 3 + col / 3;
        return !(rows[row].contains(num) || cols[col].contains(num) || boxes[boxIndex].contains(num));
    }

    private void placeNumber(int row, int col, char num) {
        int boxIndex = (row / 3) * 3 + col / 3;
        board[row][col] = num;
        rows[row].add(num);
        cols[col].add(num);
        boxes[boxIndex].add(num);
    }

    private void removeNumber(int row, int col, char num) {
        int boxIndex = (row / 3) * 3 + col / 3;
        board[row][col] = '.';
        rows[row].remove(num);
        cols[col].remove(num);
        boxes[boxIndex].remove(num);
    }

    private void placeNextNumbers(int row, int col) {
        // 到达最后一格时表示已解决
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        } else {
            // 换行
            if (col == N - 1) {
                backtrack(row + 1, 0);
            } else {
                backtrack(row, col + 1);
            }
        }
    }

    /**
     * 38. 报数
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char current = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 字符变化时存入信息,否则增加计数
            if (c != current) {
                sb.append(count).append(current);
                current = c;
                count = 1;
            } else {
                count++;
            }
        }
        // 到达最后一位时存入信息
        sb.append(count).append(current);
        return sb.toString();
    }

    private int[] candidates;

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        Arrays.sort(candidates);
        this.candidates = candidates;
        backtrackCombinationSum(target, 0);
        return result;
    }

    private void backtrackCombinationSum(int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backtrackCombinationSum(target - candidates[i], i);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 43. 字符串相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        if (l1 == 0 || l2 == 0) {
            return "";
        }
        int[] resultArray = new int[l1 + l2];
        int i = l1 - 1;
        for (; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int each = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + resultArray[i + j + 1];
                resultArray[i + j] += each / 10;
                resultArray[i + j + 1] = each % 10;
            }
        }
        // 找到第一个非0位，避免(1000 * 0)情况出错
        while (resultArray[++i] == 0) {
            if (i == resultArray.length - 1) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (i < l1 + l2) {
            sb.append(resultArray[i]);
            i++;
        }
        return sb.toString();
    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> param = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return recursion(param);
    }

    private List<List<Integer>> recursion(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.size() == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums.get(0));
            result.add(list);
            return result;
        }
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> removed = new ArrayList<>();
            removed.addAll(0, nums);
            removed.remove(i);
            List<List<Integer>> temp = recursion(removed);
            for (List<Integer> list : temp) {
                list.add(0, nums.get(i));
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();
        backtrack(numList, result, 0);
        return result;
    }

    private void backtrack(List<Integer> nums, List<List<Integer>> result, int index) {
        int size = nums.size();
        if (size == index) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(0, nums);
            result.add(temp);
        }
        for (int i = index; i < size; i++) {
            Collections.swap(nums, index, i);
            backtrack(nums, result, index + 1);
            Collections.swap(nums, index, i);
        }
    }

    /**
     * 53. 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }

    /**
     * 54. 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int x1 = 0, y1 = 0;
        int x2 = matrix[0].length - 1;
        int y2 = matrix.length - 1;

        while (x1 <= x2 && y1 <= y2) {
            for (int i = x1; i <= x2; i++) {
                result.add(matrix[y1][i]);
            }
            y1++;
            if (y1 > y2) {
                break;
            }
            for (int i = y1; i <= y2; i++) {
                result.add(matrix[i][x2]);
            }
            x2--;
            if (x1 > x2) {
                break;
            }
            for (int i = x2; i >= x1; i--) {
                result.add(matrix[y2][i]);
            }
            y2--;
            for (int i = y2; i >= y1; i--) {
                result.add(matrix[i][x1]);
            }
            x1++;
        }
        return result;
    }

    /**
     * 59. 螺旋矩阵 II
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int current = 1;
        int x1 = 0, y1 = 0, x2 = n - 1, y2 = n - 1;
        while (current <= n * n) {
            for (int i = x1; i <= x2; i++) {
                matrix[y1][i] = current++;
            }
            y1++;
            for (int i = y1; i <= y2; i++) {
                matrix[i][x2] = current++;
            }
            x2--;
            for (int i = x2; i >= x1; i--) {
                matrix[y2][i] = current++;
            }
            y2--;
            for (int i = y2; i >= y1; i--) {
                matrix[i][x1] = current++;
            }
            x1++;
        }
        return matrix;
    }

    /**
     * 61. 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        int length = 1;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        int actualK = length - k % length;
        temp.next = head;
        for (int i = 0; i < actualK; i++) {
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] result = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int temp;
                if (i == 0 || j == 0) {
                    temp = 1;
                } else {
                    temp = result[j][i - 1] + result[j - 1][i];
                }
                result[j][i] = temp;
            }
        }
        return result[m - 1][n - 1];
    }

    public int uniquePaths2(int m, int n) {
        // 初始化对应行的数组
        int[] rows = new int[n];
        Arrays.fill(rows, 1);
        // 通过上一列数据自增获取当前列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                rows[j] += rows[j - 1];
            }
        }
        return rows[n - 1];
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // 记录前两级台阶方法数
        int i = 1, j = 2;
        for (int k = 0; k < n - 3; k++) {
            i = i + 2 * j;
            j = i - j;
            i = i - j;
        }
        return i + j;
    }

    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        result.add(new ArrayList<>());
        result.add(Collections.singletonList(nums[0]));
        for (int i = 1; i < len; i++) {
            List<List<Integer>> resultTemp = new ArrayList<>();
            List<Integer> temp;
            for (List<Integer> sub : result) {
                temp = new ArrayList<>(sub);
                temp.add(nums[i]);
                resultTemp.add(temp);
            }
            result.addAll(result.size(), resultTemp);
        }
        return result;
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        for (int k = 0; k < m + n; k++) {
            int n1 = i < m ? temp[i] : Integer.MAX_VALUE;
            int n2 = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (n1 < n2) {
                nums1[i + j] = n1;
                i++;
            } else {
                nums1[i + j] = n2;
                j++;
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? nums1[i] : Integer.MIN_VALUE;
            int n2 = j >= 0 ? nums2[j] : Integer.MIN_VALUE;
            if (n1 > n2) {
                nums1[i + j + 1] = n1;
                i--;
            } else {
                nums1[i + j + 1] = n2;
                j--;
            }
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int depth = 1;
        depth += Math.max(maxDepth(root.left), maxDepth(root.right));
        return depth;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        int length = prices.length;
        if (length <= 1) {
            return max;
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (prices[i] < prices[j]) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        int length = prices.length;
        if (length <= 1) {
            return maxProfit;
        }
        for (int i = 0; i < length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (maxProfit < prices[i] - minPrice) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfitSecond(int[] prices) {
        int maxProfit = 0;
        int length = prices.length;
        if (length <= 1) {
            return maxProfit;
        }
        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    private int maxPathSum = 0;

    /**
     * 124. 二叉树中的最大路径和
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxPathSum = root.val;
        recursion(root);
        return maxPathSum;
    }

    private int recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = recursion(root.left);
        int rightSum = recursion(root.right);
        // 计算当前节点路径和
        maxPathSum = Math.max(maxPathSum, leftSum + root.val + rightSum);
        int currentNodeSum = Math.max(leftSum + root.val, root.val + rightSum);
        if (currentNodeSum < 0) {
            currentNodeSum = 0;
        }
        return currentNodeSum;
    }

    /**
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        int singleSum = 0;
        for (int num : nums) {
            set.add(num);
            sum += num;
        }
        for (Integer num : set) {
            singleSum += num;
        }
        return singleSum * 2 - sum;
    }

    /**
     * 141. 环形链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (!set.contains(head)) {
                set.add(head);
            } else {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 142. 环形链表 II
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (!set.contains(head)) {
                set.add(head);
            } else {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode node1 = head;
        ListNode node2 = slow;

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    /**
     * 146. LRU缓存机制
     */
    static class LRUCache {

        LinkedHashMap<Integer, Integer> map;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>(capacity);
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                int val = map.remove(key);
                map.put(key, val);
                return val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.remove(key);
            } else if (map.size() == capacity) {
                Integer key1 = map.entrySet().iterator().next().getKey();
                map.remove(key1);
            }
            map.put(key, value);
        }
    }

    static class LRUCache2 extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * 148. 排序链表
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(node -> node.val));
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;
        return list.get(0);
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0, sortLength = 1;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            length++;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode dummyTemp, left, right;
        int i, leftLength, rightLength;
        while (sortLength < length) {
            dummyTemp = dummy;
            current = dummy.next;
            while (current != null) {
                i = sortLength;
                // cut
                left = current;
                while (i > 0 && current != null) {
                    current = current.next;
                    i--;
                }
                if (i > 0) {
                    break;
                }
                right = current;
                i = sortLength;
                while (i > 0 && current != null) {
                    current = current.next;
                    i--;
                }
                leftLength = sortLength;
                rightLength = sortLength - i;
                // merge
                while (leftLength > 0 && rightLength > 0) {
                    if (left.val > right.val) {
                        dummyTemp.next = right;
                        right = right.next;
                        rightLength--;
                    } else {
                        dummyTemp.next = left;
                        left = left.next;
                        leftLength--;
                    }
                    dummyTemp = dummyTemp.next;
                }
                dummyTemp.next = leftLength == 0 ? right : left;
                while (leftLength > 0 || rightLength > 0) {
                    dummyTemp = dummyTemp.next;
                    leftLength--;
                    rightLength--;
                }
            }
            sortLength *= 2;
        }
        return dummy.next;
    }

    /**
     * 155. 最小栈
     */
    static class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> helper;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            helper = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (helper.isEmpty() || x <= helper.peek()) {
                helper.add(x);
            }
        }

        public void pop() {
            if (helper.peek().equals(stack.pop())) {
                helper.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return helper.peek();
        }
    }

    /**
     * 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointerA = headA, pointerB = headB, endA = null, endB = null;
        while (pointerA != null && pointerB != null) {
            if (pointerA.equals(pointerB)) {
                return pointerA;
            }
            if (pointerA.next == null) {
                if (endB != null && !pointerA.equals(endB)) {
                    return null;
                }
                endA = pointerA;
                pointerA = headB;
            } else {
                pointerA = pointerA.next;
            }
            if (pointerB.next == null) {
                if (endA != null && !pointerB.equals(endA)) {
                    return null;
                }
                endB = pointerB;
                pointerB = headA;
            } else {
                pointerB = pointerB.next;
            }
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }

    /**
     * 169. 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public int majorityElement2(int[] nums) {
        int candidate = nums[0], count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += num == candidate ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1->2<-3<-4(子节点已翻转完成)
        ListNode node = reverseList2(head.next);
        // 1<->2<-3<-4
        head.next.next = head;
        // null<-1<-2<-3<-4
        head.next = null;
        return node;
    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // N logN
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        // N logK
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n));
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int result = 0;
        if (heap.size() > 0) {
            result = heap.peek();
        }
        return result;
    }

    int[] nums;

    public int findKthLargest3(int[] nums, int k) {
        // 平均 N 最坏 N2
        this.nums = nums;
        int length = nums.length;
        return quickSelect(0, length - 1, length - k);
    }

    private int partition(int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        int actualPivotIndex = left;
        // 先将中位数放到最右边
        swap(pivotIndex, right);
        // 将比中位数小的都移到左边
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(i, actualPivotIndex);
                actualPivotIndex++;
            }
        }
        // 将中位数放到实际的中位上
        swap(actualPivotIndex, right);
        return actualPivotIndex;
    }

    private int quickSelect(int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int pivotIndex = partition(left, right, left);
        if (pivotIndex == k) {
            return nums[pivotIndex];
        } else if (pivotIndex > k) {
            return quickSelect(left, pivotIndex - 1, k);
        } else {
            return quickSelect(pivotIndex + 1, right, k);
        }
    }

    private void swap(int i1, int i2) {
        if (i1 == i2) {
            return;
        }
        nums[i1] += nums[i2];
        nums[i2] = nums[i1] - nums[i2];
        nums[i1] = nums[i1] - nums[i2];
    }

    /**
     * 217. 存在重复元素
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        // 二叉搜索树是排序树，按中序搜索可以得到升序序列
        List<Integer> list = new ArrayList<>();
        fillTreeNodeList(root, list);
        return list.get(k - 1);
    }

    private void fillTreeNodeList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        fillTreeNodeList(root.left, list);
        list.add(root.val);
        fillTreeNodeList(root.right, list);
    }

    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
    }

    /**
     * 231. 2的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        return isPowerOfTwo(n / 2);
    }

    public boolean isPowerOfTwo2(int n) {
        // 0100 & 0011 = 0000
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 二叉搜索树的左子树节点都小于当前节点，右子树节点都大于当前节点
        int val = root.val;
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;
        while (node != null) {
            val = node.val;
            if (pVal < val && qVal < val) {
                node = node.left;
            } else if (pVal > val && qVal > val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }


    /**
     * 237. 删除链表中的节点
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 238. 除自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        long product = 1;
        int zeroTimes = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroTimes++;
            } else {
                product *= num;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (zeroTimes == 0) {
                result[i] = (int) (product / nums[i]);
            } else if (zeroTimes == 1) {
                if (nums[i] == 0) {
                    result[i] = (int) product;
                } else {
                    result[i] = 0;
                }
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        // 使用左侧数组乘积乘以右侧数组乘积
        int length = nums.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right *= nums[i];
        }
        return result;
    }

    /**
     * 292. Nim 游戏
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * 344. 反转字符串
     *
     * @param s
     */
    public void reverseString(char[] s) {
        char temp;
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            temp = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = temp;
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            char[] chars = str.toCharArray();
            reverseString(chars);
            sb.append(chars);
            sb.append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private TreeNode lca = null;

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        reverseTree(root, p, q);
        return lca;
    }

    private boolean reverseTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        int mid = root == p || root == q ? 1 : 0;
        int left = reverseTree(root.left, p, q) ? 1 : 0;
        int right = reverseTree(root.right, p, q) ? 1 : 0;
        if (mid + left + right == 2) {
            lca = root;
        }
        return mid + left + right > 0;
    }

    /**
     * 剑指 Offer 45. 把数组排成最小的数
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int num : nums) {
            result.add(String.valueOf(num));
        }
        result.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder sb = new StringBuilder();
        for (String str : result) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 快排
     *
     * @param nums
     */
    public void quicksort(int[] nums, int l, int r) {
        // 结束递归
        if (l >= r) {
            return;
        }
        int temp = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= temp) {
                j--;
            }
            // 将比基准数小的放到左边
            nums[i] = nums[j];
            while (i < j && nums[i] <= temp) {
                i++;
            }
            // 将比基准数大的放到右边
            nums[j] = nums[i];
        }
        // 将基准数放到正确的位置
        nums[i] = temp;
        quicksort(nums, l, i - 1);
        quicksort(nums, i + 1, r);
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return 0;
    }

    /**
     * 剑指 Offer 49. 丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(n2, Math.min(n3, n5));
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

    /**
     * 130. 被围绕的区域
     *
     * @param board
     */
    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        // 从上下边界开始搜索
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        // 从左右边界开始搜索
        for (int i = 1; i < n - 1; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }
        // 恢复标记并修改未标记O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }

    // 二维数组宽、高
    private int m, n;

    /**
     * 深度优先搜索
     *
     * @param board
     * @param x
     * @param y
     */
    public void dfs(char[][] board, int x, int y) {
        // 到达边界或者遇到不为O(X/A)的就返回
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || board[y][x] != 'O') {
            return;
        }
        // 标记连接点
        board[y][x] = 'A';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }

    public void solveBfs(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[n - 1][i] == 'O') {
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{0, i});
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{m - 1, i});
            }
        }
        // 检查每个与边界'O'相连的点
        while (!queue.isEmpty()) {
            int[] temp = queue.remove();
            int row = temp[1];
            int col = temp[0];
            board[row][col] = 'A';
            if (row + 1 < n && board[row + 1][col] == 'O') {
                queue.offer(new int[]{col, row + 1});
            }
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                queue.offer(new int[]{col, row - 1});
            }
            if (col + 1 < m && board[row][col + 1] == 'O') {
                queue.offer(new int[]{col + 1, row});
            }
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                queue.offer(new int[]{col - 1, row});
            }
        }
        // 恢复标记并修改未标记O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * N个人围成一个圆圈进行报数, 报偶数者出列, 剩下的人继续报数
     * <p>
     * int f(int n, int m)
     * return n == 1 ? n : (f(n - 1, m) + m - 1) % n + 1;
     * old = (new + m) % n 由于编号从1开始需要先-后+
     *
     * @param n
     * @return
     */
    public Object[] countOffInACircle(int n) {
        List<Integer> result = new ArrayList<>(n);
        ListNode current = createCircularLinkedList(n);
        ListNode pre = null;
        int i = 1;
        while (current.next != current) {
            if (i != 2) {
                i++;
                pre = current;
                current = current.next;
            } else {
                i = 1;
                result.add(current.val);
                // remove current
                pre.next = current.next;
                current = pre.next;
            }
        }
        result.add(current.val);
        return result.toArray();
    }

    /**
     * 创建环形链表
     *
     * @param n
     * @return
     */
    private ListNode createCircularLinkedList(int n) {
        ListNode head = new ListNode(1);
        ListNode pre = head;
        for (int i = 1; i < n; i++) {
            pre.next = new ListNode(i + 1);
            pre = pre.next;
        }
        // 闭环
        pre.next = head;
        return head;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private HashMap<Node, Node> visited = new HashMap<>();

    /**
     * 133. 克隆图
     * <p>
     * dfs
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    public Node cloneGraphBfs(Node node) {
        if (node == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        visited.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) {
            return false;
        }
        int m = matrix[0].length;
        if (m == 0) {
            return false;
        }
        int row = 0, col = m - 1;
        while (row < n && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 05. 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 546. 移除盒子
     *
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoint(boxes, dp, 0, boxes.length - 1, 0);
    }

    public int calculatePoint(int[] boxes, int[][][] dp, int l, int r, int k) {
        // end condition
        if (l > r) {
            return 0;
        }
        // avoid double counting
        if (dp[l][r][k] > 0) {
            return dp[l][r][k];
        }
        // merge external same boxes
        while (r > l && boxes[r - 1] == boxes[r]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoint(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        // find internal same box
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoint(boxes, dp, l, i, k + 1) + calculatePoint(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int[] result;
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        int size = stack.size();
        result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    /**
     * 733. 图像渲染
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] temp = queue.remove();
            int row = temp[0], col = temp[1];
            image[row][col] = newColor;
            if (row - 1 >= 0 && image[row - 1][col] == oldColor) {
                queue.offer(new int[]{row - 1, col});
            }
            if (row + 1 < n && image[row + 1][col] == oldColor) {
                queue.offer(new int[]{row + 1, col});
            }
            if (col - 1 >= 0 && image[row][col - 1] == oldColor) {
                queue.offer(new int[]{row, col - 1});
            }
            if (col + 1 < m && image[row][col + 1] == oldColor) {
                queue.offer(new int[]{row, col + 1});
            }
        }
        return image;
    }

    public int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        n = image.length;
        m = image[0].length;
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int row, int col, int oldColor, int newColor) {
        if (row < 0 || row >= n || col < 0 || col >= m || image[row][col] != oldColor) {
            return;
        }
        image[row][col] = newColor;
        dfs(image, row + 1, col, oldColor, newColor);
        dfs(image, row - 1, col, oldColor, newColor);
        dfs(image, row, col + 1, oldColor, newColor);
        dfs(image, row, col - 1, oldColor, newColor);
    }

    /**
     * 110. 平衡二叉树
     * <p>
     * top down
     * 先判断自己是不是平衡,然后判断左右子树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public boolean isBalancedBottomUp(TreeNode root) {
        return heightBottomUp(root) >= 0;
    }

    private int heightBottomUp(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 先判断左右子树是不是平衡，再判断当前节点
        int leftHeight = heightBottomUp(node.left);
        int rightHeight = heightBottomUp(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 109. 有序链表转换二叉搜索树
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        // 让fast从下一节点开始使得slow到达中点左侧
        // 123(odd) slow为1 1234(even) slow为2
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 记录根节点
        ListNode root = slow.next;
        // 解链
        slow.next = null;
        return new TreeNode(root.val, sortedListToBST(head), sortedListToBST(root.next));
    }

    /**
     * 647. 回文子串
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int count = 0;
        int length = s.length();
        for (int i = 0; i < length * 2 - 1; i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < length && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                count++;
            }
        }
        return count;
    }

    /**
     * 529. 扫雷游戏
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        n = board.length;
        m = board[0].length;
        int x = click[1], y = click[0];
        if (board[y][x] == 'M') {
            board[y][x] = 'X';
        } else {
            updateBoardDfs(board, x, y);
        }
        return board;
    }

    private void updateBoardDfs(char[][] board, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || board[y][x] != 'E') {
            return;
        }
        int[] dirX = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
        int[] dirY = new int[]{0, 1, 0, -1, 1, -1, 1, -1};
        int num = 0;
        for (int i = 0; i < dirX.length; i++) {
            int tempX = x + dirX[i];
            int tempY = y + dirY[i];
            if (tempX >= 0 && tempX < m && tempY >= 0 && tempY < n && board[tempY][tempX] == 'M') {
                num++;
            }
        }
        if (num > 0) {
            board[y][x] = (char) (num + '0');
        } else {
            board[y][x] = 'B';
            for (int i = 0; i < dirX.length; i++) {
                int tempX = x + dirX[i];
                int tempY = y + dirY[i];
                updateBoardDfs(board, tempX, tempY);
            }
        }
    }

    /**
     * 224. 基本计算器
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        int result = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                operand = 10 * operand + (ch - '0');
            } else if (ch == '+') {
                result += sign * operand;

                sign = 1;
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;

                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);

                sign = 1;
                result = 0;
            } else if (ch == ')') {
                result += sign * operand;
                result *= stack.pop();
                result += stack.pop();
                operand = 0;
            }
        }
        return result + (sign * operand);
    }

    /**
     * 227. 基本计算器 II
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        int operand = 0, preOperand = 0, result = 0;
        char preOperate = '+';
        // 保证最后一个数也被使用
        s += '#';
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                operand = operand * 10 + (c - '0');
            } else {
                switch (preOperate) {
                    case '+':
                        result += preOperand;
                        preOperand = operand;
                        break;
                    case '-':
                        result += preOperand;
                        preOperand = -operand;
                        break;
                    case '*':
                        preOperand *= operand;
                        break;
                    case '/':
                        preOperand /= operand;
                        break;
                }
                preOperate = c;
                operand = 0;
            }
        }
        return result + preOperand;
    }

    /**
     * 772. 基本计算器 III
     *
     * @param s
     * @return
     */
    public int calculate3(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                switch (c) {
                    case '[':
                    case '{':
                        c = '(';
                        break;
                    case ']':
                    case '}':
                        c = ')';
                        break;
                }
                queue.offer(c);
            }
        }
        // 保证最后一个数也被使用
        queue.offer('#');
        return calculate3(queue);
    }

    private int calculate3(Queue<Character> queue) {
        int operand = 0, preOperand = 0, result = 0;
        char preOperate = '+';
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (c >= '0' && c <= '9') {
                operand = operand * 10 + (c - '0');
            } else if (c == '(') {
                operand = calculate3(queue);
            } else {
                switch (preOperate) {
                    case '+':
                        result += preOperand;
                        preOperand = operand;
                        break;
                    case '-':
                        result += preOperand;
                        preOperand = -operand;
                        break;
                    case '*':
                        preOperand *= operand;
                        break;
                    case '/':
                        preOperand /= operand;
                        break;
                }
                if (c == ')') {
                    break;
                }
                preOperate = c;
                operand = 0;
            }
        }
        return result + preOperand;
    }

    int i = 0;

    public int calculate4(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        char preOperate = '+';
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '[':
                case '{':
                    c = '(';
                    break;
                case ']':
                case '}':
                    c = ')';
                    break;
            }
            if (c >= '0' && c <= '9') {
                operand = operand * 10 + (c - '0');
            }
            if (c == '(') {
                i++;
                operand = calculate4(s);
            }
            if (c < '0' || c > '9' || i == s.length() - 1) {
                switch (preOperate) {
                    case '+':
                        stack.push(operand);
                        break;
                    case '-':
                        stack.push(-operand);
                        break;
                    case '*':
                        stack.push(stack.pop() * operand);
                        break;
                    case '/':
                        stack.push(stack.pop() / operand);
                        break;
                }
                preOperate = c;
                operand = 0;
            }
            if (c == ')') {
                break;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, minDepth(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, minDepth(root.right));
        }
        return min + 1;
    }

    /**
     * 679. 24 点游戏
     *
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return judgePoint24(list);
    }

    private boolean judgePoint24(List<Double> list) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            return Math.abs(list.get(0) - 24) < 1e-6;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                List<Double> subList = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if (k != i && k != j) {
                        subList.add(list.get(k));
                    }
                }
                for (int s = 0; s < 4; s++) {
                    // + * 满足交换律
                    if (s < 2 && i > j) {
                        continue;
                    }
                    Double num1 = list.get(i), num2 = list.get(j);
                    switch (s) {
                        case 0:
                            subList.add(num1 + num2);
                            break;
                        case 1:
                            subList.add(num1 * num2);
                            break;
                        case 2:
                            subList.add(num1 - num2);
                            break;
                        case 3:
                            if (Math.abs(num2) < 1e-6) {
                                continue;
                            } else {
                                subList.add(num1 / num2);
                            }
                            break;
                    }
                    if (judgePoint24(subList)) {
                        return true;
                    }
                    subList.remove(subList.size() - 1);
                }
            }
        }
        return false;
    }

    /**
     * 201. 数字范围按位与
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }

    /**
     * 459. 重复的子字符串
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    private List<List<Integer>> result;
    private List<Integer> temp;

    /**
     * 491. 递增子序列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        dfs(0, Integer.MIN_VALUE, nums);
        return result;
    }

    private void dfs(int cur, int pre, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[cur] >= pre) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        // 相等时不会被跳过(避免重复[1, 0] [0, 1])
        if (nums[cur] != pre) {
            dfs(cur + 1, pre, nums);
        }
    }

    /**
     * 657. 机器人能否返回原点
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        int[] move = new int[4];
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'R':
                    move[0] = move[0] + 1;
                    break;
                case 'L':
                    move[1] = move[1] + 1;
                    break;
                case 'U':
                    move[2] = move[2] + 1;
                    break;
                case 'D':
                    move[3] = move[3] + 1;
                    break;
            }
        }
        return move[0] == move[1] && move[2] == move[3];
    }

    private Map<String, PriorityQueue<String>> map;
    private List<String> itinerary;

    /**
     * 332. 重新安排行程
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        itinerary = new ArrayList<>();
        for (List<String> path : tickets) {
            String key = path.get(0);
            if (!map.containsKey(key)) {
                map.put(key, new PriorityQueue<>());
            }
            map.get(key).offer(path.get(1));
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    private void dfs(String from) {
        while (map.containsKey(from) && map.get(from).size() > 0) {
            String temp = map.get(from).poll();
            dfs(temp);
        }
        itinerary.add(from);
    }

    /**
     * 214. 最短回文串
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > best; i--) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }

    private Set<Integer> keys;

    /**
     * 841. 钥匙和房间
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        keys = new HashSet<>();
        dfs(rooms, 0);
        return keys.size() == rooms.size();
    }

    private void dfs(List<List<Integer>> rooms, int key) {
        keys.add(key);
        for (int k : rooms.get(key)) {
            if (!keys.contains(k)) {
                dfs(rooms, k);
            }
        }
    }

    /**
     * 486. 预测赢家
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        int total = total(nums, 0, nums.length - 1, 1);
        return total >= 0;
    }

    private int total(int[] nums, int l, int r, int sign) {
        if (l == r) {
            return nums[l] * sign;
        }
        int lScore = nums[l] * sign + total(nums, l + 1, r, -sign);
        int rScore = nums[r] * sign + total(nums, l, r - 1, -sign);
        if (sign > 0) {
            return Math.max(lScore, rScore);
        } else {
            return Math.min(lScore, rScore);
        }
    }

    public boolean PredictTheWinnerDP(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        // 只剩一个数时最大差就是该数
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        // 根据转移方程计算
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    /**
     * 877. 石子游戏
     * true
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[] dp = new int[length];
        System.arraycopy(piles, 0, dp, 0, length);
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] > 0;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        String regex = "[ ]*[-,+]?(([0-9]+([.][0-9]*)?)|([0-9]*([.][0-9]+)))([e,E][-,+]?[0-9]+)?[ ]*";
        return s.matches(regex) && s.trim().length() != 0;
    }

    private Set<Integer> columns;
    private Set<Integer> diagonals1;
    private Set<Integer> diagonals2;
    private List<List<String>> NQueens;
    private int[] queens;

    /**
     * 51. N 皇后
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        columns = new HashSet<>();
        diagonals1 = new HashSet<>();
        diagonals2 = new HashSet<>();
        NQueens = new ArrayList<>();
        queens = new int[n];
        Arrays.fill(queens, -1);
        backtrackNQueens(n, 0);
        return NQueens;
    }

    private void backtrackNQueens(int n, int row) {
        if (row == n) {
            NQueens.add(generateBoard(n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) {
                continue;
            }
            // \斜线行减列不变
            int diagonal1 = row - i;
            if (diagonals1.contains(diagonal1)) {
                continue;
            }
            // /斜线行加列不变
            int diagonal2 = row + i;
            if (diagonals2.contains(diagonal2)) {
                continue;
            }
            queens[row] = i;
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);
            backtrackNQueens(n, row + 1);
            queens[row] = -1;
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
        }
    }

    private List<String> generateBoard(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    private List<String> treePaths;

    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        treePaths = new ArrayList<>();
        temp = new ArrayList<>();
        if (root == null) {
            return treePaths;
        }
        backtrackFindPaths(root);
        return treePaths;
    }

    private void backtrackFindPaths(TreeNode node) {
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int path : temp) {
                sb.append(path);
                sb.append("->");
            }
            sb.append(node.val);
            treePaths.add(sb.toString());
        }
        temp.add(node.val);
        if (node.left != null) {
            backtrackFindPaths(node.left);
        }
        if (node.right != null) {
            backtrackFindPaths(node.right);
        }
        temp.remove(temp.size() - 1);
    }

    /**
     * 60. 第k个排列
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new LinkedList<>();
        // (n - 1)!
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }
        factorial /= n;
        while (k != 0) {
            int temp = k / factorial;
            k %= factorial;
            if (k == 0) {
                sb.append(list.remove(--temp));
                for (int i = list.size() - 1; i >= 0; i--) {
                    sb.append(list.get(i));
                }
            } else {
                sb.append(list.remove(temp));
                factorial /= list.size();
            }
        }
        return sb.toString();
    }

    /**
     * 107. 二叉树的层次遍历 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(0, temp);
        }
        return result;
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        backtrackCombine(1, n, k);
        return result;
    }

    private void backtrackCombine(int i, int n, int k) {
        if (n - i + 1 + temp.size() < k) {
            return;
        }
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(i);
        backtrackCombine(i + 1, n, k);
        temp.remove(temp.size() - 1);
        backtrackCombine(i + 1, n, k);
    }

    private Map<Integer, Integer> candidatesMap;

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        candidatesMap = new HashMap<>();
        Arrays.sort(candidates);
        this.candidates = candidates;
        for (int i : candidates) {
            candidatesMap.put(i, candidatesMap.getOrDefault(i, 0) + 1);
        }
        backtrackCombinationSum2(0, target);
        return result;
    }

    private void backtrackCombinationSum2(int i, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (i == candidates.length || target < 0) {
            return;
        }
        backtrackCombinationSum2(i + candidatesMap.get(candidates[i]), target);
        int maxTime = Math.min(target / candidates[i], candidatesMap.get(candidates[i]));
        for (int j = 1; j <= maxTime; j++) {
            temp.add(candidates[i]);
            backtrackCombinationSum2(i + candidatesMap.get(candidates[i]), target - candidates[i] * j);
        }
        for (int j = 1; j <= maxTime; j++) {
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        backtrackCombinationSum3(1, k, n);
        return result;
    }

    private void backtrackCombinationSum3(int i, int k, int n) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (i > 9 || n < 0 || k == 0) {
            return;
        }
        if (k == 1 && n <= 9 && n >= i) {
            temp.add(n);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        temp.add(i);
        backtrackCombinationSum3(i + 1, k - 1, n - i);
        temp.remove(temp.size() - 1);
        backtrackCombinationSum3(i + 1, k, n);
    }

    /**
     * 637. 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                sum += temp.val;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    private boolean isEnd;

    /**
     * 79. 单词搜索
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.isEnd = false;
        n = board.length;
        m = board[0].length;
        boolean[][] isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    dfsExist(j, i, 0, word, isVisited);
                    if (isEnd) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void dfsExist(int col, int row, int i, String word, boolean[][] isVisited) {
        if (board[row][col] != word.charAt(i)) {
            return;
        }
        if (i == word.length() - 1) {
            isEnd = true;
            return;
        }
        isVisited[row][col] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !isVisited[newRow][newCol]) {
                dfsExist(newCol, newRow, i + 1, word, isVisited);
                if (isEnd) {
                    break;
                }
            }
        }
        isVisited[row][col] = false;
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            result.addAll(inorderTraversal(root.left));
        }
        result.add(root.val);
        if (root.right != null) {
            result.addAll(inorderTraversal(root.right));
        }
        return result;
    }

    /**
     * 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }

    /**
     * 685. 冗余连接 II
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int conflict = -1, cycle = -1;
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int u = edge[0], v = edge[1];
            if (parent[v] != v) {
                conflict = i;
            } else {
                parent[v] = u;
                if (uf.find(u) == uf.find(v)) {
                    cycle = i;
                } else {
                    uf.union(u, v);
                }
            }
        }

        if (conflict < 0) {
            return new int[]{edges[cycle][0], edges[cycle][1]};
        } else {
            if (cycle < 0) {
                return new int[]{edges[conflict][0], edges[conflict][1]};
            } else {
                return new int[]{parent[edges[conflict][1]], edges[conflict][1]};
            }
        }
    }

    class UnionFind {
        int[] ancestor;

        public UnionFind(int n) {
            ancestor = new int[n];
            for (int i = 0; i < n; ++i) {
                ancestor[i] = i;
            }
        }

        public void union(int index1, int index2) {
            ancestor[find(index1)] = find(index2);
        }

        public int find(int index) {
            if (ancestor[index] != index) {
                ancestor[index] = find(ancestor[index]);
            }
            return ancestor[index];
        }
    }

    private boolean[] vis;

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrackPermuteUnique(nums, 0);
        return result;
    }

    private void backtrackPermuteUnique(int[] nums, int i) {
        if (i == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            temp.add(nums[j]);
            vis[j] = true;
            backtrackPermuteUnique(nums, i + 1);
            temp.remove(temp.size() - 1);
            vis[j] = false;
        }
    }

    /**
     * 617. 合并二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int sum = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }

    private int sum = 0;

    /**
     * 538. 把二叉搜索树转换为累加树
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    /**
     * 968. 监控二叉树
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        return 0;
    }

}


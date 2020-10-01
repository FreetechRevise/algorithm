package com.ywh.problem.leetcode.medium;

/**
 * 最长回文子串
 * 解法类似 647，具体解析参考 {@link LeetCode647}
 * <p>
 * [字符串] [动态规划]
 *
 * @author ywh
 * @since 2/28/2019
 */
public class LeetCode5 {

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int n = s.length(), start = 0, maxLen = 0;

        // 二维数组 dp[i][j] 记录 i~j 是否为回文子串。
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // 单个字符都是回文串：a
                if (i == j) {
                    dp[i][j] = true;
                }
                // 两个字符，相同即为回文串：a, a
                else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                // 两个以上的字符，只要边界相等且内部为回文串，整体即为回文串：a, [...], a，其中 [...] = b, b
                else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 求以 left、right 为中心的回文子串最大长度
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expand(String s, int left, int right) {
        for (; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left--, right++) ;
        return (right - 1) - (left + 1) + 1;
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param s
     * @return
     */
    public String longestPalindromeExpand(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        // 记录最大回文子串的开始位置和长度。
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 处理 a, b, b, a 和 a, b, a 两种回文串。
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

}

package com.ywh.problem.leetcode.easy;

/**
 * 二进制求和
 * [数学] [字符串]
 * 
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 *      输入: a = "11", b = "1"
 *      输出: "100"
 * 示例 2:
 *      输入: a = "1010", b = "1011"
 *      输出: "10101"
 * 提示：
 *      每个字符串仅由字符 '0' 或 '1' 组成。
 *      1 <= a.length, b.length <= 10^4
 *      字符串如果不是 "0" ，就都不含前导零。
 * 
 * @author ywh
 * @since 04/04/2020
 */
public class LeetCode67 {

    /**
     * Time: O(max(m, n)), Space: O(max(m, n))
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int idxA = a.length() - 1, idxB = b.length() - 1, carry = 0, sum;
        while (idxA >= 0 || idxB >= 0 || carry != 0) {
            sum = carry;
            if (idxA >= 0) {
                sum += a.charAt(idxA--) - '0';
            }
            if (idxB >= 0) {
                sum += b.charAt(idxB--) - '0';
            }
            // sb.append(sum & 1);
            sb.append(sum % 2);
            // carry = sum >> 1;
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}

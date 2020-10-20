package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 零钱兑换
 * [动态规划]
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode322 {

    /**
     * DP 状态数组设计参考 {@link LeetCode518}
     *
     * Time: O(n*sum), Space: O(n*sum)
     *
     * @param coins
     * @param sum
     * @return
     */
    public int minCoinCombination(int[] coins, int sum) {

        // d[i][j] 表示使用前 i 种面值的硬币（0 ~ i - 1）凑成数值 j 的最小硬币数
        int[][] d = new int[coins.length + 1][sum + 1];

        // 把凑成各种面值的最小硬币数初始化成最大值，表示默认凑不齐给定值
        // 尽管题目要求凑不成时返回 - 1，但负数不利于大小判断，所以设置成一个不影响判断的值、最后返回再特殊处理
        for (int j = 0; j <= sum; j++) {
            d[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= sum; j++) {
                // 使用当前面值凑 i 元所用币数
                int useCurCoin = j >= coins[i - 1] ? d[i][j - coins[i - 1]] : Integer.MAX_VALUE;
                if (useCurCoin != Integer.MAX_VALUE) {
                    useCurCoin += 1;
                }
                // 与使用上一种面值凑 j 元对比，取较小者
                d[i][j] = Math.min(d[i - 1][j], useCurCoin);
            }
        }

        return d[coins.length][sum] == Integer.MAX_VALUE ? -1 : d[coins.length][sum];
    }

    /**
     * Time: O(n*sum), Space: O(sum)
     *
     * @param coins
     * @param sum
     * @return
     */
    public int minCoinCombinationOsum(int[] coins, int sum) {
        int[] dp = new int[sum + 1];

        // d[i] 表示凑成 i 元的最小硬币数，其中 d[0] == 0 表示凑成 0 元的最小硬币数为 0，其余先初始化为 Integer.MAX_VALUE - 1
        Arrays.fill(dp, 1, sum + 1, Integer.MAX_VALUE - 1);

        // 遍历每种面值的硬币，用来凑 [0, sum] 元。
        for (int coin : coins) {
            // 从当前硬币面值开始凑（因为假设用 5 元硬币凑 1 元就无意义，所以至少凑 5 元），使用面值为 coin 的硬币凑 sum：
            // 比如要用 2 元硬币凑 10 元，如果之前已经把凑成 10 - 2 == 8 元的最小硬币数算好，存在两种可能：
            //      1. 使用这个硬币，加上之前凑的 8 元，刚好 10 元（硬币数 + 1）。
            //      2. 不使用这个硬币，通过其他方法凑成的 10 元。
            // 取其较小者即可。
            for (int j = coin; j <= sum; dp[j] = Math.min(dp[j], dp[j - coin] + 1), j++);
        }
        return dp[sum] == Integer.MAX_VALUE - 1 ? -1 : dp[sum];
    }
}
package com.ywh.problem.leetcode.medium;

/**
 * 路径数量（含障碍物）
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 05/11/2019
 */
public class LeetCode63 {

    /**
     * 区别于 {@link LeetCode63} 如果当前位置有障碍物（grid[i][j] == 1），则到当前位置的路径数量为 0
     * <p>
     * Time: O(m*n), Space: O(m*n)
     *
     * @param grid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Time: O(m*n), Space: O(n)
     *
     * @param grid
     * @return
     */
    public int uniquePathsWithObstaclesOn(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {
            dp[0] = grid[i][0] == 1? 0: dp[0];
            for (int j = 1; j < n; j++) {
                // dp[j] 更新前可视为到达上方格子的路径数，而 dp[j - 1] 为到达左方各自的路径数
                dp[j] = grid[i][j] == 1? 0: dp[j] + dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}

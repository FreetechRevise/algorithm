package com.ywh.problem.leetcode.medium;

/**
 * 搜索二维矩阵 II
 * [二分查找] [分治]
 * 
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例 1：
 *       输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 *      输出：true
 * 示例 2：
 *      输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 *      输出：false
 * 提示：
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= n, m <= 300
 *      -10^9 <= matrix[i][j] <= 10^9
 *      每行的所有元素从左到右升序排列
 *      每列的所有元素从上到下升序排列
 *      -10^9 <= target <= 10^9
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode240 {

    /**
     * 对比 {@link LeetCode74} 取出每行后可以重新编排成一行、然后用二分查找。
     * 本题是每行都作为一个独立的递增数组。
     *
     * Time: O(m+n), Space: O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchIn2DArray(int[][] matrix, int target) {
        // 从右上角开始遍历。
        for (int i = 0, j = matrix[0].length - 1; i < matrix.length && j >= 0; ) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

}

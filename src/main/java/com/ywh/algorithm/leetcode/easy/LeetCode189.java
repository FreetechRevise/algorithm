package com.ywh.algorithm.leetcode.easy;

/**
 * 旋转数组
 * [数组]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode189 {

    /**
     * 使用辅助数组实现
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     */
    public void rotateByCopy(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }

        int n = nums.length, m = k % n, i = 0;

        // 临时辅助数组，取原数组后 m 位复制为新数组前 m 位
        int[] tmp = new int[n];
        for (int j = n - m; j < n; j++) {
            tmp[i++] = nums[j];
        }

        // 取原数组前 n - m 位复制为新数组后 n - m 位
        for (int j = 0; j < n - m; j++) {
            tmp[i++] = nums[j];
        }

        // 把辅助数组的所有元素复制到原数组
        for (int j = 0; j < n; j++) {
            nums[j] = tmp[j];
        }
    }

    /**
     * 把数组 nums 从 i 到 j 之间的元素逆序
     *
     * @param nums
     * @param i
     * @param j
     */
    private void reverse(int[] nums, int i, int j) {
        for (; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    /**
     * 通过三轮逆序交换实现
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @param k
     */
    public void rotateBySwap(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }

        int n = nums.length, m = k % n;

        // 整体逆序：[1, 2, 3, 4, 5] => [5, 4, 3, 2, 1]
        reverse(nums, 0, n - 1);

        // 前 m 位逆序：[5, 4, 3, 2, 1] => [4, 5, 3, 2, 1]
        reverse(nums, 0, m - 1);

        // 后 n - m 位逆序：[4, 5, 3, 2, 1] => [4, 5, 1, 2, 3]
        reverse(nums, m, n - 1);
    }

}

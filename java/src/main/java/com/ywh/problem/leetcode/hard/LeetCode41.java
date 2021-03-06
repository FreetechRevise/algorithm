package com.ywh.problem.leetcode.hard;

/**
 * 缺失的第一个正数
 * [数组]
 * 
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * 示例 1：
 *      输入：nums = [1,2,0]
 *      输出：3
 * 示例 2：
 *      输入：nums = [3,4,-1,1]
 *      输出：2
 * 示例 3：
 *      输入：nums = [7,8,9,11,12]
 *      输出：1
 * 提示：
 *      0 <= nums.length <= 300
 *      -2^31 <= nums[i] <= 2^31 - 1
 *
 * @author ywh
 * @since 2020/9/15/015
 */
public class LeetCode41 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;

        // 2, -1, 4, 1, 8

        // nums[2-1] != 2, swap
        //  +----+
        //  |    ↓
        // [2], -1, 4, 1, 8     =>      [-1], 2, 4, 1, 8

        // nums[-1-1] < 1, skip
        // [-1], 2, 4, 1, 8

        // nums[2-1] == 2, skip
        // -1, [2], 4, 1, 8

        // nums[4-1] != 4, swap
        //         +---+
        //         |   ↓
        // -1, 2, [4], 1, 8     =>      -1, 2, [1], 4, 8

        // nums[1-1] != 1, swap
        //  +------+
        //  ↓      |
        // -1, 2, [1], 4, 8     =>      1, 2, [-1], 4, 8

        // nums[4-1] == 4, skip
        // 1, 2, -1, [4], 8

        // 8 > n, skip
        // 1, 2, -1, 4, [8]

        //
        for (int i = 0; i < n; ) {
            int num = nums[i];
            // 设位置 i 的值为 num，如果 num 在 [1, n] 的范围内，且数组中第 num 个值不为 num，则把位置 i 的值与位置 num-1 的值交换。
            if (num >= 1 && num <= n && nums[num - 1] != num) {
                int tmp = nums[i];
                nums[i] = nums[num - 1];
                nums[num - 1] = tmp;
            }
            // 否则表示值已经正确归位，如 [1, -1, 3, ...]，处理下一个值。
            else {
                ++i;
            }
        }

        // -1 != 3, return i + 1
        // 1, 2, [-1], 4, 8

        // 遍历交换好的数组，找到第一个未正确归位的值，返回该位置“原来应有的值”。
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 否则认为全部归位，返回最后一个值即可。
        return n + 1;
    }

    /**
     * FIXME bug
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveFlipSign(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        // 2, [-1], 4, 1, 8       =>      2, [6], 4, 1, 8
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 1) {
                nums[i] = n + 1;
            }
        }
        // 2, 6, 4, 1, 8

        // abs(nums[0]) => 2
        // nums[2-1] > 0, set
        // [2], (6), 4, 1, 8      =>      [2], (-6), 4, 1, 8

        // abs(nums[1]) => 6
        // 6 > n, skip
        // 2, [-6], 4, 1, 8

        // abs(nums[2]) == 4
        // nums[4-1] > 0, set
        // 2, -6, [4], (1), 8     =>      2, -6, [4], -1, 8

        // abs(nums[3]) == 1
        // nums[1-1] > 0, set
        // (2), -6, 4, [-1], 8    =>      -2, -6, 4, [-1], 8

        // abs(nums[4]) == 8
        // 8 > n, skip
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n && nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1];
            }
        }

        // -2, -6, 4, [-1], 8
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}

package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 包含重复元素的数组排列
 * [回溯] [哈希表]
 * <p>
 * TODO 补充其他解法
 *
 * @author ywh
 * @since 05/04/2020
 */
public class LeetCode47 {

    private void permuteRec(List<Integer> nums, int start, Set<List<Integer>> result) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            permuteRec(nums, start + 1, result);
            Collections.swap(nums, start, i);
        }
    }

    /**
     * Time: O(n*n!), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueUsingHashSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Set<List<Integer>> result = new HashSet<>();
        permuteRec(list, 0, result);
        return new ArrayList<>(result);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    private boolean nextPermutation(int[] nums) {
        int n = nums.length, p = nums.length - 2;

        // 游标 p 从右到左移动，找到第一个递减的点
        // 9, [1], (8, 4, 2, 1)
        while (p >= 0 && nums[p] >= nums[p + 1]) {
            --p;
        }
        // 如果游标 p 未越界，则游标 i 从最右开始开始向左移动，找到第一个比 p 所指元素小的元素，交换 i、p 所指元素（把稍大的换上来）
        if (p >= 0) {
            int i = n - 1;
            while (i > p && nums[i] <= nums[p]) {
                --i;
            }
            swap(nums, i, p);
        }

        // 最后对 p + 1 到 n - 1 之间的元素按从大到小排序（从高位到低位，从小到大）
        // 由于 p 移动到该位置时已确定此时是从小到大排序，两两交换即可逆序
        for (int left = p + 1, right = n - 1; left < right; ++left, --right) {
            swap(nums, left, right);
        }

        // 返回是否存在下一个更大的排列
        return p != -1;
    }

    /**
     * 先排序，再不断求比当前排列（表示的整数）大的下一个排列添加到结果集
     * 参考 {@link LeetCode31}
     *
     * Time: O(n*n!), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueUsingNextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        result.add(toList(nums));
        while (nextPermutation(nums)) {
            result.add(toList(nums));
        }
        return result;
    }

}
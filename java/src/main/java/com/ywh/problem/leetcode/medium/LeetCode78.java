package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 * [数组] [回溯] [位操作]
 *
 * @author ywh
 * @since 23/11/2019
 */
public class LeetCode78 {

    /**
     *
     * @param nums
     * @param start
     * @param elem
     * @param ret
     */
    private void subsets(int[] nums, int start, LinkedList<Integer> elem, List<List<Integer>> ret) {

        // 此处添加子集（第一次调用 elem 为空、添加的是空集）
        ret.add(new ArrayList<>(elem));
        for (int i = start; i < nums.length; i++) {
            elem.add(nums[i]);
            subsets(nums, i + 1, elem, ret);

            // 回溯，每次移除最后一个元素
            elem.removeLast();
        }
    }


    /**
     * Time: O(2^n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ret = new ArrayList<>();
        subsets(nums, 0, new LinkedList<>(), ret);
        return ret;
    }

    /**
     * TODO 暂时未理解
     *
     * Time: O(n*2^n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsBit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length, N = (int) Math.pow(2, n);

        for (int i = 0; i < N; ++i) {
            List<Integer> elem = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    elem.add(nums[j]);
                }
            }
            ret.add(elem);
        }
        return ret;
    }

}

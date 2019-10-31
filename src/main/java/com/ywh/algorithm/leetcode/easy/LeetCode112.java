package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.Stack;


/**
 * 路径和是否等于给定值
 * [树] [DFS]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode112 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSumRecursive(root.left, sum - root.val) || hasPathSumRecursive(root.right, sum - root.val);

    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumIterative(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // 使用两个栈，一个用于缓存节点，另一个用于缓存从根节点到当前节点的差值（给定值 - 路径和）
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        stack.push(root);
        sumStack.push(sum);

        while (!stack.isEmpty()) {
            int s = sumStack.pop();
            TreeNode n = stack.pop();

            // 到达叶子节点，且和正好等于给定值
            if (n.left == null && n.right == null && n.val == s) {
                return true;
            }
            if (n.left != null) {
                stack.push(n.left);
                sumStack.push(s - n.val);
            }
            if (n.right != null) {
                stack.push(n.right);
                sumStack.push(s - n.val);
            }
        }

        return false;
    }
}

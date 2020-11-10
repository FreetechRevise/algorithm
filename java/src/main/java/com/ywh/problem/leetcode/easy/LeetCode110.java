package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

/**
 * 判断二叉树是否平衡
 * [树] [DFS]
 *
 * @author ywh
 * @since 2/18/2019
 */
public class LeetCode110 {

    /**
     * 求二叉树高度
     *
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * Time: O(nlog(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isBalancedTreeTopDown(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left, right = root.right;
        return Math.abs(getHeight(left) - getHeight(right)) < 2
            && isBalancedTreeTopDown(left)
            && isBalancedTreeTopDown(right);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isBalancedTreeBottomUp(TreeNode root) {
        return getHeightAndCheck(root) != -1;
    }

    /**
     * 求树的高度，如果不平衡，则在求高度过程中直接返回 -1。
     *
     * @param root
     * @return
     */
    private int getHeightAndCheck(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeightAndCheck(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeightAndCheck(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}

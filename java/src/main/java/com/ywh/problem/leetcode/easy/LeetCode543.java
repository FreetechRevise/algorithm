package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;
import com.ywh.problem.leetcode.hard.LeetCode124;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 二叉树的直径
 * [树]
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * @author ywh
 * @since 2019/11/4/004
 */
public class LeetCode543 {

    /**
     *
     * @param root
     * @param ret
     * @return
     */
    private int maxDepth(TreeNode root, int[] ret) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left, ret), right = maxDepth(root.right, ret);
        ret[0] = Math.max(ret[0], left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * 对比 {@link LeetCode124}
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeRecursive(TreeNode root) {
        int[] ret = new int[1];
        maxDepth(root, ret);
        return ret[0];
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int diameter = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // 记录节点子树及其深度
        Map<TreeNode, Integer> depthMap = new HashMap<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            // 优先把左节点入栈，再把右节点入栈，已记录深度的节点跳过
            if (node.left != null && !depthMap.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !depthMap.containsKey(node.right)) {
                stack.push(node.right);
            }
            // 如果栈顶元素左右节点为空，则弹出
            else {
                stack.pop();
                int left = depthMap.getOrDefault(node.left, 0);
                int right = depthMap.getOrDefault(node.right, 0);

                // 左右节点深度之和为直径，求最大值
                diameter = Math.max(diameter, left + right);

                // 取左右节点直径中的较大者，+1 缓存为当前节点深度
                depthMap.put(node, Math.max(left, right) + 1);
            }
        }
        return diameter;
    }
}
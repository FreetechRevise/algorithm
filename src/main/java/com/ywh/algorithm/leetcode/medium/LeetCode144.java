package com.ywh.algorithm.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树前序遍历
 * [栈] [树]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode144 {

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
                // stack.push(left);
                // root = root.left;
            } else {
                root = stack.pop().right;
                // node = stack.pop();
                // result.add(node.val);
                // root = node.right;
            }
        }
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right!= null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}

package binarytree;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class MaximumDepthOfBinaryTree {
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}

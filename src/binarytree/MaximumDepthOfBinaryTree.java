package binarytree;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class MaximumDepthOfBinaryTree {
    // 分解子问题递归思想
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }


    }

    // 二叉树遍历思想
    class Solution2 {
        // 记录最大深度
        int res = 0;
        // 记录遍历到的节点的深度
        int depth = 0;

        public int maxDepth(TreeNode root) {
            traverse(root);
            return res;
        }

        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            depth++;
            if (root.left == null && root.right == null) {
                // 到达叶子节点，更新最大深度
                res = Math.max(res, depth);
            }
            traverse(root.left);
            traverse(root.right);
            // 后序位置
            depth--;
        }
    }
}

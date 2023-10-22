package binarytree;

/**
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {
    // 每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和。
    class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxDiameter;
        }

        int maxDiameter = 0;

        // 返回二叉树最大深度
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            maxDiameter = Math.max(leftMax + rightMax, maxDiameter);

            return 1 + Math.max(leftMax, rightMax);
        }
    }
}

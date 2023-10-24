package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBST {
    class Solution2 {
        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return res;
        }

        // 记录结果
        int res = 0;
        // 记录当前元素的排名
        int rank = 0;

        void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            traverse(root.left, k);
            /* 中序遍历代码位置 */
            rank++;
            if (k == rank) {
                // 找到第 k 小的元素
                res = root.val;
                return;
            }

            traverse(root.right, k);
        }
    }

    class Solution3 {
        List<Integer> list = new ArrayList<>();

        public int kthSmallest(TreeNode root, int k) {
            traverse(root);
            return list.get(k - 1);
        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.left);
            list.add(root.val);
            traverse(root.right);
        }
    }
}

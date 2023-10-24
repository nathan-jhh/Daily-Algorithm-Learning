package binarytree;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class ValidateBinarySearchTree {

    // 二叉搜索树的中序遍历结果是升序的
    class Solution {
        long pre = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            // 访问左子树
            if (!isValidBST(root.left)) {
                return false;
            }
            // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            // 访问右子树
            return isValidBST(root.right);
        }
    }

    class Solution2 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
        boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            // base case
            if (root == null) return true;
            // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
            if (min != null && root.val <= min.val) return false;
            if (max != null && root.val >= max.val) return false;
            // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left, min, root)
                    && isValidBST(root.right, root, max);
        }
    }

}

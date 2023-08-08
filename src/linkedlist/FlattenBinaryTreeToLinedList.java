package linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，
 * 而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/
 */
public class FlattenBinaryTreeToLinedList {
    public static void main(String[] args) {

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {
        List<TreeNode> list = new ArrayList<TreeNode>();

        public void flatten(TreeNode root) {
            preOrderTraversal(root);
            TreeNode cur = root;
            for (int i = 1; i < list.size(); i++) {
                cur.right = list.get(i);
                cur.left = null;
                cur = cur.right;
            }
        }

        // 前序遍历保存节点到list
        private void preOrderTraversal(TreeNode root) {
            if (root == null){
                return;
            }
            list.add(root);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
}

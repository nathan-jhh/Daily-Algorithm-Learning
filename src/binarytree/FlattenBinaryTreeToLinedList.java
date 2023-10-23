package binarytree;

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
            if (root == null) {
                return;
            }
            list.add(root);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    // 原地拉伸
    static class Solution2 {
        // 定义：输入节点 root，将以root为根的二叉树拉平为一条链表
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            flatten(root.left);
            flatten(root.right);

            // 将左子树作为右子树
            TreeNode rightNode = root.right;
            root.right = root.left;
            root.left = null;

            // 将原先的右子树接到当前右子树的末端
            TreeNode tempNode = root; // 这里用root，不要用root.right
            while (tempNode.right != null) {
                tempNode = tempNode.right;
            }
            tempNode.right = rightNode;
        }
    }
}

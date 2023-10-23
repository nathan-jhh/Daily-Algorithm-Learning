package binarytree;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTree {
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder, 0, preorder.length - 1,
                    inorder, 0, inorder.length - 1);
        }

        // 函数定义：若前序遍历数组为 preorder[preStart..preEnd]，
        // 中序遍历数组为 inorder[inStart..inEnd]，
        // 构造二叉树，返回该二叉树的根节点
        private TreeNode build(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) {
                return null;
            }
            int rootVal = preorder[preStart];

            // rootVal 在中序遍历数组中的索引
            int index = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    index = i;
                    break;
                }
            }

            TreeNode root = new TreeNode(rootVal);
            int leftSize = index - inStart;
            root.left = build(preorder, preStart + 1, preStart + leftSize,
                    inorder, inStart, index - 1);
            root.right = build(preorder, preStart + leftSize + 1, preEnd,
                    inorder, index + 1, inEnd);
            return root;
        }
    }
}

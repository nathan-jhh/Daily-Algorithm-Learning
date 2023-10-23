package binarytree;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/
 */
public class PopulatingNextRightPointers {
    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            if (root.left != null) {
                root.left.next = root.right;
                // 需要将右节点与右边的相邻节点也连接起来
                root.right.next = root.next != null ? root.next.left : null;

                // 一个节点的事情做完后，再递归
                connect(root.left);
                connect(root.right);
            }
            return root;
        }
    }

    class Solution2 {
        public Node connect(Node root) {
            if (root == null) return null;
            connectTwoNode(root.left, root.right);
            return root;
        }

        // 递归函数定义：连接左节点和右节点，连接左节点的左右节点，连接右节点的左右节点，
        // 且连接左节点右孩子和右节点左孩子
        private void connectTwoNode(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return;
            }

            // 将传入的两个节点连接
            node1.next = node2;

            // 连接相同父节点的两个子节点
            connectTwoNode(node1.left, node1.right);
            connectTwoNode(node2.left, node2.right);
            // 连接跨越父节点的两个子节点
            connectTwoNode(node1.right, node2.left);
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

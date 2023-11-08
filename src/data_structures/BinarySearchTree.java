package data_structures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    //不包含重复元素的二分搜索树

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //向二分搜索树添加新的元素
    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    //查询二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //查询以node为根节点的二分搜索树中是否包含元素e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //前序遍历,深度优先遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        //操作
        System.out.print(node.e + " ");

        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的非递归前序遍历实现
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    //中序遍历,遍历后的结果有序的
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);

        //遍历操作
        System.out.print(node.e + " ");

        inOrder(node.right);
    }

    //后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);

        //遍历操作
        System.out.print(node.e + " ");
    }

    //层序遍历，广度优先遍历
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            //遍历操作
            System.out.print(node.e + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    //寻找二分搜索树中最小的元素,递归写法
    public E min() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return min(root).e;
    }

    //返回以node为根的二分搜索树的最小元素所在的节点
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    //寻找二分搜索树中最小的元素,非递归写法，Not Recursive
    public E minNR() {
        Node curNode = root; //很重要，不能直接用root
        while (curNode.left != null) {
            curNode = curNode.left;
        }
        return curNode.e;
    }

    // 寻找二分搜索树的最大元素
    public E max() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return max(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    //删除二分搜索树中的最小元素所在节点，并返回最小元素,递归写法
    public E deleteMin() {
        E res = min();
        root = deleteMin(root);
        return res;
    }

    //删除掉以node为根的二分搜索树中的最小节点，并返回删除节点后新的二分搜索树的根
    private Node deleteMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点,返回最大元素
    public E deleteMax() {
        E ret = max();
        root = deleteMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node deleteMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = deleteMax(node.right);
        return node;
    }

    //从二分搜索树中删除指定元素e
    public void delete(E e) {
        root = delete(root, e);
    }

    //从以node为根的二分搜索树中删除指定元素所在节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    public Node delete(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = delete(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = delete(node.right, e);
            return node;
        } else {    //此时就是要删除这个节点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //左右子树都不为空的情况

            //找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node node1 = min(node.right);
            node1.right = deleteMin(node.right);
            node1.left = node.left;
            //size--; //在这里是不需要维护size的，因为在函数deleteMin(Node node)中已经维护了size
            node.left = null;
            node.right = null;
            return node1;
        }
    }
}

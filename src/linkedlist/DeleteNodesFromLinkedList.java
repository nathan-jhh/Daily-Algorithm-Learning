package linkedlist;

/**
 * 1474. 删除链表 M 个节点之后的 N 个节点
 * 给定链表 head 和两个整数 m 和 n. 遍历该链表并按照如下方式删除节点:
 * 开始时以头节点作为当前节点.
 * 保留以当前节点开始的前 m 个节点.
 * 删除接下来的 n 个节点.
 * 重复步骤 2 和 3, 直到到达链表结尾.
 * 在删除了指定结点之后, 返回修改过后的链表的头节点.
 * https://leetcode.cn/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/?
 * envType=study-plan-v2&envId=premium-algo-100
 */
public class DeleteNodesFromLinkedList {
    public static void main(String[] args) {
        /*int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        ListNode listNode = new ListNode(nums);
        System.out.println("listNode = " + listNode);

        ListNode newListNode = new Solution().deleteNodes(listNode, 1, 3);
        System.out.println("newListNode = " + newListNode);*/

        int[] nums2 = new int[]{6, 3, 5, 6, 2, 8, 9, 2, 3, 4};
        ListNode listNode2 = new ListNode(nums2);
        System.out.println("listNode2 = " + listNode2);

        ListNode newListNode2 = new Solution().deleteNodes(listNode2, 2, 1);
        System.out.println("newListNode2 = " + newListNode2);
    }

    // 递归
    static class Solution {
        public ListNode deleteNodes(ListNode head, int m, int n) {
            if (head == null) {
                return null;
            }

            // 第m个节点
            ListNode mNode = head;
            // 第n+m+1个节点
            ListNode mnNode = head;
            for (int i = 0; i < m - 1; i++) {
                if (mNode.next == null) {
                    break;
                }
                mNode = mNode.next;
            }
            for (int i = 0; i < m + n; i++) {
                mnNode = mnNode.next;
                if (mnNode == null) {
                    break;
                }
            }

            mNode.next = deleteNodes(mnNode, m, n);
            return head;
        }
    }
}

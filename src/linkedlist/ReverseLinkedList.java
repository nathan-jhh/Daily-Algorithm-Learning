package linkedlist;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        ListNode listNode = new ListNode(nums);
        System.out.println("listNode = " + listNode);

        ListNode newListNode = new Solution2().reverseList(listNode);
        System.out.println("newListNode = " + newListNode);
    }

    // recursion
    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return head;
            }

            ListNode node = head.next;
            ListNode newHead = reverseList(head.next);
            node.next = head;
            head.next = null;
            return newHead;
        }
    }

    // loop
    static class Solution2 {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode nextNode = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nextNode;
            }
            return prev;
        }
    }
}

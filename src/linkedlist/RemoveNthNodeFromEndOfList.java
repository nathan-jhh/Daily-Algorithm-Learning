package linkedlist;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-interview-150
 */
public class RemoveNthNodeFromEndOfList {
    // 递归 + 回溯
    class Solution {
        int i = 0;

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            head.next = removeNthFromEnd(head.next, n);
            // 回溯
            i++;
            if (i == n) {
                head = head.next;
            }
            return head;
        }
    }

    // 双指针
    class Solution2 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyHead = new ListNode(-1, head);
            // 找到待删除节点的前驱结点
            ListNode nth = findNthFromEnd(dummyHead, n + 1);
            nth.next = nth.next.next;
            return dummyHead.next;
        }

        // 返回倒数第n个节点
        private ListNode findNthFromEnd(ListNode head, int n) {
            ListNode cur = head;
            while (n > 0) {
                cur = cur.next;
                n--;
            }
            ListNode cur2 = head;
            while (cur != null) {
                cur2 = cur2.next;
                cur = cur.next;
            }
            return cur2;
        }
    }

    // 暴力
    class Solution3 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head.next == null) {
                return null;
            }

            int size = 0;
            ListNode cur = head;
            size++;
            // 找到待删除节点的前驱结点
            while (cur.next != null) {
                size++;
                cur = cur.next;
            }

            if (size == n) {
                return head.next;
            }

            ListNode cur2 = head;
            for (int i = 1; i <= size - n - 1; i++) {
                cur2 = cur2.next;
            }
            cur2.next = cur2.next.next;

            return head;
        }
    }
}

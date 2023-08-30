package linkedlist;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * https://leetcode.cn/problems/partition-list/
 */
public class PartitionList {

    // recursion
    class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode node = partition(head.next, x);
            if (head.val < x) {
                head.next = node;
                return head;
            } else {
                // 可能head之后并没有小于x的节点，这种情况不用动，直接返回head
                if (node.val > x) {
                    head.next = node;
                    return head;
                }

                ListNode dummy = new ListNode(-1, node);
                ListNode cur = dummy;
                while (cur.next != null && cur.next.val < x) {
                    cur = cur.next;
                }
                head.next = cur.next;
                cur.next = head;
                return dummy.next;
            }
        }
    }

    // two pointer
    class Solution2 {
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode small = new ListNode(-1);
            ListNode large = new ListNode(-1);
            ListNode smallDummyHead = small;
            ListNode largeDummyHead = large;
            ListNode cur = head;
            while (cur != null) {
                if (cur.val < x) {
                    small.next = cur;
                    small = small.next;
                } else {
                    large.next = cur;
                    large = large.next;
                }
                cur = cur.next;
            }
            large.next = null;
            small.next = largeDummyHead.next;
            return smallDummyHead.next;
        }
    }
}

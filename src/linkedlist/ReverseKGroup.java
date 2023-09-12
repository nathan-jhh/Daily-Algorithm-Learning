package linkedlist;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯地改变节点内部的值，而是需要实际进行节点交换。
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 6};
        ListNode listNode = new ListNode(nums);
        System.out.println("listNode = " + listNode);

        ListNode newListNode = new ReverseKGroup.Solution2().reverseKGroup(listNode, 3);
        System.out.println("newListNode = " + newListNode);
    }

    // recursion
    static class Solution {
        ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            // 区间 [a, b) 包含 k 个待反转元素
            ListNode a, b;
            a = b = head;
            for (int i = 0; i < k; i++) {
                // 不足 k 个，不需要反转，base case
                if (b == null) {
                    return head;
                }
                b = b.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverse(a, b);
            // 递归反转后续链表并连接起来
            a.next = reverseKGroup(b, k);
            return newHead;
        }

        /**
         * 反转区间 [a, b) 的元素，注意是左闭右开
         */
        ListNode reverse(ListNode a, ListNode b) {
            ListNode pre, cur, next;
            pre = null;
            cur = a;
            next = a;
            // while 终止的条件改一下就行了
            while (cur != b) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 返回反转后的头结点
            return pre;
        }
    }

    // recursion
    static class Solution2 {
        ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode pre = dummy;
            ListNode end = dummy;

            while (end.next != null) {
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }
                if (end == null) break;
                ListNode start = pre.next;
                ListNode next = end.next;
                end.next = null;
                pre.next = reverse(start);
                start.next = next;
                pre = start;

                end = pre;
            }
            return dummy.next;
        }

        private ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }
    }
}

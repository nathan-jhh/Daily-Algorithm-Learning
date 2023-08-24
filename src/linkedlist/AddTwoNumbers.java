package linkedlist;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * https://leetcode.cn/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class AddTwoNumbers {
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(-1, null);
            ListNode cur = dummyHead;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                int int1 = l1 == null ? 0 : l1.val;
                int int2 = l2 == null ? 0 : l2.val;
                int add = int1 + int2 + carry;
                carry = add >= 10 ? 1 : 0;
                cur.next = new ListNode(add >= 10 ? add - 10 : add);
                cur = cur.next;
                if (l1 != null)
                    l1 = l1.next;
                if (l2 != null)
                    l2 = l2.next;
            }
            return dummyHead.next;
        }
    }

    // recursion
    class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            int add = l1.val + l2.val;
            ListNode res = new ListNode(add % 10);
            res.next = addTwoNumbers(l1.next, l2.next);
            if (add >= 10) {
                res.next = addTwoNumbers(res.next, new ListNode(1));
            }
            return res;
        }
    }
}

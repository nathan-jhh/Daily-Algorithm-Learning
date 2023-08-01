package linkedlist;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/
 */
public class DeleteNodeInLinedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode listNode = new ListNode(nums);
        System.out.println("listNode = " + listNode);

        //ListNode listNodeNew = new Solution().deleteNode(listNode, 6);
        //System.out.println("listNodeNew = " + listNodeNew);

        ListNode listNodeNew2 = new Solution2().deleteNode(listNode, 6);
        System.out.println("listNodeNew2 = " + listNodeNew2);
    }

    // 递归解法
    static class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            // 递归调用deleteNode(head.next, val)后，返回已经删除节点后的链表的头结点，然后再挂接到head后边
            head.next = deleteNode(head.next, val);
            return head.val == val ? head.next : head;
        }
    }

    // 循环
    static class Solution2 {
        public ListNode deleteNode(ListNode head, int val) {
            // 创建虚拟头结点，简化判空操作
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;

            ListNode prev = dummyHead;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummyHead.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        // 使用arr作为参数，创建一个链表，当前this链表为链表头节点
        public ListNode(int arr[]) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("arr can not be empty");
            }

            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                res.append(cur.val + "->");
                cur = cur.next;
            }
            res.append("null");
            return res.toString();
        }
    }
}


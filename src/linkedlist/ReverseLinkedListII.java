package linkedlist;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5};
        ListNode listNode = new ListNode(nums);
        System.out.println("listNode = " + listNode);

        ListNode newListNode = new Solution2().reverseBetween(listNode, 1, 2);
        System.out.println("newListNode = " + newListNode);
    }

    // loop
    static class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 当需要创造一条新链表时，往往可以使用虚拟头结点避免分类讨论
            // use dummy head
            ListNode dummyNode = new ListNode(-1, head);

            ListNode preLeftNode = dummyNode;
            for (int i = 0; i < left - 1; i++) {
                preLeftNode = preLeftNode.next;
            }
            ListNode leftNode = preLeftNode.next;

            ListNode rightNode = dummyNode;
            for (int i = 0; i < right; i++) {
                rightNode = rightNode.next;
            }
            ListNode afterRightNode = rightNode.next;

            // cut a smaller linkedlist
            preLeftNode.next = null;
            rightNode.next = null;

            reverseList(leftNode);

            preLeftNode.next = rightNode;
            leftNode.next = afterRightNode;

            return dummyNode.next;
        }

        // reverse linkedlist
        private ListNode reverseList(ListNode head) {
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

    // recursion
    static class Solution2 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (left == 1) {
                return reverseN(head, right);
            }
            // 前进到反转的起点触发
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

        // 反转以 head 为起点的 n 个节点，返回新的头结点
        private ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                return head;
            }
            // 以 head.next 为起点，需要反转前 n - 1 个节点
            ListNode last = reverseN(head.next, n - 1);

            // 执行reverseN函数后，head.next的下一个节点指向的应当是last节点原本的next节点
            ListNode successor = head.next.next;
            head.next.next = head;
            // 让反转之后的 head 节点和后面的节点连起来
            head.next = successor;
            return last;
        }
    }
}

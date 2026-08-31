/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1, prev = -1;
        int min = Integer.MAX_VALUE;
        int index = 1;

        ListNode a = head;
        ListNode b = head.next;

        while (b.next != null) {
            if ((b.val > a.val && b.val > b.next.val) ||
                (b.val < a.val && b.val < b.next.val)) {

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - prev);
                }

                prev = index;
            }

            a = b;
            b = b.next;
            index++;
        }

        if (first == -1 || first == prev)
            return new int[]{-1, -1};

        return new int[]{min, prev - first};
    }
}
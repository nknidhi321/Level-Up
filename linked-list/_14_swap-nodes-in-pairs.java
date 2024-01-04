// https://leetcode.com/problems/swap-nodes-in-pairs/

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
    
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        
        // Create dummy head and tail
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = head.next;
        ListNode forw = head.next.next;
        
        while(curr != null && next != null) {
            // Swapping nodes
            curr.next = forw;
            next.next = curr;
            prev.next = next;
            
            // Pointer increment
            prev = curr;
            curr = forw;
            if(curr != null) next = curr.next;
            if(next != null) forw = next.next;
        }
        return dummy.next;
    }
    
}

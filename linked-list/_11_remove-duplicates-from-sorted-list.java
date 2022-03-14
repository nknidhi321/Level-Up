// https://leetcode.com/problems/remove-duplicates-from-sorted-list/

class Solution {
    
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode dummy = new ListNode(-101);
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            if(prev.val != curr.val) {
                prev.next = curr;   
                prev = prev.next;
            }
            curr = curr.next;
        }
        prev.next = null;
        return dummy.next;
    }
}

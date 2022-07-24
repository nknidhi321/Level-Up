// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

class Solution {
    
    public ListNode swapNodes(ListNode head, int k1) {
        if(head == null || head.next == null) return head;
        
        // Cal. size of LL
        int size = 0;
        ListNode temp = head;
        while(temp != null) {
            size++;
            temp = temp.next;
        }
        
        // Aage se size - k1 + 1 == piche se k 
        int k2 = size - k1 + 1; 
        
        ListNode ptr1 = null;
        ListNode ptr2 = null;
        temp = head;
        
        // Pointing pt1 && ptr2 to the req indexes, to swap later on
        int count = 0;
        while(temp != null) {
            count++;
            if(count == k1) ptr1 = temp;
            if(count == k2) ptr2 = temp;
            
            if(ptr1 != null && ptr2 != null) break;
            temp = temp.next;
        }
        
        // Swapping node values
        int t = ptr1.val;
        ptr1.val = ptr2.val;
        ptr2.val = t;
        return head;
    }
    
}

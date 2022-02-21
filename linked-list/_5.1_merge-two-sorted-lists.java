// https://leetcode.com/problems/merge-two-sorted-lists/

class Solution {
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        // Maintain dummy othewise you will have to write more if's statement while creating the very 1st node 
        ListNode dummy = new ListNode(-1); 
        
        // The smaller list(curr) will lastly point to null in the while loop,
        // after that adding the remaining node to the end of the samller list 
        // So we have to keep track of the prev node beforehand so that when curr points to null we point to the prev of curr
        ListNode prev = dummy;
        
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                prev.next = curr1;
                curr1 = curr1.next;
            }
            else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        
        prev.next = curr1 != null ? curr1 : curr2; // Adding the remaining nodes to the end of smaller list
        return dummy.next; // return dummy.next because 1st node was useless
    }
}

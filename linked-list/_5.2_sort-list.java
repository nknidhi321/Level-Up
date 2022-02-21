// https://leetcode.com/problems/sort-list/

class Solution {
    
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode mid = mid(head);
        ListNode nhead = mid.next; // head of 2nd LL
        mid.next = null; // Setting 1st half LL end to null
        
        // Divide and conquer, recursively divide and then merge
        return mergeTwoSortedLists(sortList(head), sortList(nhead));
    }
    
    
    // 1st mid
    public ListNode mid(ListNode head) {
      if(head == null || head.next == null) return head;  

      ListNode slow = head; 
      ListNode fast = head;

      while(fast.next != null && fast.next.next != null) {   // fast will never be null here
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }
    
    
    // Merging 2 sorted lists
    public ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode dummy = new ListNode(-1);
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
        
        prev.next = curr1 != null ? curr1 : curr2;
        return dummy.next; 
    }
    
}

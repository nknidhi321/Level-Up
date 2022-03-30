// https://leetcode.com/problems/linked-list-cycle/

public class Solution {
    
    public boolean hasCycle(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast) return true;
        }
        return false;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// https://leetcode.com/problems/linked-list-cycle-ii/
// Floyd cycle detection ALgo

public class Solution {
    
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        // Catch up the pt. where slow and fast meets for the very first time
        ListNode meet = null;
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {   // while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                meet = slow;
                break;
            }
        }
        if(meet == null) return null;  // No cycle found
        
        // Now keep a pointer at head and another ptr at, where slow and fast ptrs. met 
        // Now move both at speed of 1, they will meet again where the cycle starts. 
        ListNode temp1 = head;
        ListNode temp2 = meet;
        while(temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

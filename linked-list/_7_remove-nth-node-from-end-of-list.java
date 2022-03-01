// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Both codes same logic, only edge case handling is diff

// Using dummy node, edge case is handled implicitly

// Keep 2 ptrs. at a distance of n from each other starting from dummy
// Now move both pts. by 1 step until forward.next != null
// When you will be out of the loop, your prev ptr will be at the node from where you can delete the nth node 
    
// Edge case:-  Ex : [1,2]  n = 2     =>     delete 2nd node from last, that is head
    
```
class Solution {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null) return null; // 0 or 1 node, return null
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // Keep 2 ptrs. at a distance of n from each other starting from dummy node
        // 1st pointer(prev) will be placed at dummy
        ListNode prev = dummy;
        
        // Making forward at "distance" of n fron prev ptr
        ListNode forward = dummy;
        while(n-- > 0) {
            forward = forward.next;
        }
        
        // Ex : [1,2]  n = 2     =>     delete 2nd node from last, that is head
        // Will be handled smoothly by dummy node, no need to handly manually
        
        // Moving both ptrs. by maintaing distance of n, until forward.next != null
        while(forward.next != null) {
            prev = prev.next;
            forward = forward.next;
        }
        
        // When you will be out of the loop, your prev ptr will be at the node,
        // from where you can delete the nth node from last
        prev.next = prev.next.next;
        return dummy.next;
    }
}
```

-------------------------------------------------------------

// Without using dummy node, edge case handled explicitly

// Keep 2 ptrs. at a distance of n from each other starting from head
// Now move both pts. by 1 step until forward.next != null
// When you will be out of the loop, your prev ptr will be at the node from where you can delete the nth node 
// Edge case:-  Ex : [1,2]  n = 2     =>     delete 2nd node from last, that is head
    
```
class Solution {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null) return null; // 0 or 1 node, return null
        
        // Keep 2 ptrs. at a distance of n from each other starting from head
        // 1st pointer(prev) will be placed at head
        ListNode prev = head;
        
        // Making forward at "distnce" of n fron prev ptr
        ListNode forward = head;
        while(n-- > 0) {
            forward = forward.next;
        }
        
        // Ex : [1,2]  n = 2     =>     delete 2nd node from last, that is head
        // Now, if forward is at null => You are asked to delete the head
        if(forward == null) return head.next;  
        else {
            // Moving both ptrs. by maintaing distance of n
            while(forward.next != null) {
                prev = prev.next;
                forward = forward.next;
            }
            // When you will be out of the loop, your prev ptr will be at the node,
            // from where you can delete the nth node from last
            prev.next = prev.next.next;
            return head;
        }
    }
}
```

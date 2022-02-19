https://leetcode.com/problems/middle-of-the-linked-list/
Code for 2nd middle

```
class Solution {
    
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;  // Aankh band kar k likh do saare question me, jaane anjane galti ko bacha lega hamesha #aadat
        
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {   // fast can be null here
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```

--------------------------------------------------------------------------------------------

https://nados.io/question/mid-of-linked-list?zen=true
Code for 1st middle

```
 public Node mid() {
      if(head == null || head.next == null) return head;  // Aankh band kar k likh do saare question me, jaane anjane galti ko bacha lega hamesha #aadat

      Node slow = head; 
      Node fast = head;

      while(fast.next != null && fast.next.next != null) {   // fast will never be null here
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }
```

----------------------------------------------------------------------------------------------

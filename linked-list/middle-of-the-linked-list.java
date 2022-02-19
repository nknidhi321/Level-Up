https://leetcode.com/problems/middle-of-the-linked-list/
Code for 2nd middle

```
class Solution {
    
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
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
      if(head == null || head.next == null) return head;

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

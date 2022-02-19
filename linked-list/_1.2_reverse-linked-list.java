// https://leetcode.com/problems/reverse-linked-list/
Reverse pointers of LL

Rajneesh Bhaiya
Awesome analogy for forward(just a temp node inside loop)

```
class Solution {
    
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;  // Aadat
        
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null) {
            ListNode forward = curr.next; // Break krne se pehle aage k nodes ko point karwa lo kisi se [backup]
            curr.next = prev;             // curr k next me prev node ka address daal do, Reverse ho gayi [link]
            
            prev = curr;                  // next iteration k liye prev curr pe chala jaaega [move]
            curr = forward;               // aur curr forward pe chala jaaega [move]
        }
        
        // NOTE : forward bus ek temp backup node tha loop me
        
        return prev;
    }
}
```

---------------------------------------------------------------------------------------------------------------------

Mine, bekar analogy of forward

```
class Solution {
    
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode forward = head;
        
        // * Kisi v point pe prev previous pe point karega, par curr aur forward same node(prev k next) ko point karega
        // * While loop k andar ye variables apne naam jaise kaam karenge
        
        while(curr != null) {
            forward = curr.next; // Break krne se pehle aage k nodes ko point karwa lo kisi se [backup]
            curr.next = prev;    // curr k next me prev node ka address daal do, Reverse ho gayi [link]
            
            prev = curr;         // next iteration k liye prev curr pe chala jaaega [move]
            curr = forward;      // * aur curr forward pe chala jaaega [move]
        }
        return prev;
    }
}
```

-------------------------------------------------------------------------------------------------------------------------

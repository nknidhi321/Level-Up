// https://leetcode.com/problems/add-two-numbers/

class Solution {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        // l1 = reverseList(l1); // Not asked to revese and then add in this question
        // l2 = reverseList(l2); // Not asked to revese and then add in this question
                 
        ListNode dummy = new ListNode(-1);
        
        int carry = 0;
        ListNode curr = dummy, c1 = l1, c2 = l2;
        while(c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            curr.next = new ListNode(sum % 10);
            
            // For next iteration
            carry = sum / 10;
            curr = curr.next;
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        return dummy.next;
    }
    
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

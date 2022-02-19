// https://leetcode.com/problems/palindrome-linked-list/

class Solution {
    
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode tempHead1 = head;
        
        ListNode middleNode = middleNode(head);            // tempHead2 is the last node of the original list 
        ListNode tempHead2 = reverseList(middleNode.next); // 1st middle k next wale element se reverse karna shuru karo
        
        // Agar odd length ki list huyi toh reverse wali list choti hogi ek se, islye rev(tempHead2) ko null se check karwao
        
        boolean isPali = true;
        while(tempHead2 != null) {
            if(tempHead1.val != tempHead2.val) {
                isPali = false;
                break;
            }
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        } 
        
        // LL ko last me unmodify kar k return karna chahiye, but not implemented here    
        return isPali;
    }
    
    
     // 1st middle
     public ListNode middleNode(ListNode head) {
          if(head == null || head.next == null) return head;  

          ListNode slow = head; 
          ListNode fast = head;

          while(fast.next != null && fast.next.next != null) {   // fast will never be null here
            slow = slow.next;
            fast = fast.next.next;
          }
          return slow;
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

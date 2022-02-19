// https://leetcode.com/problems/palindrome-linked-list/

Intuition : 2nd half k ptrs. ko reverse kar lo and check for palindrome
NOTE : head ko hamesha intact rakhne ki kosish karo, pointer mat bachao, extra lge toh lga lo


class Solution {
    
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true; // Aadat
        
        ListNode mid = middleNode(head);             
        
        // 1st middle k next wale element se reverse karna shuru karo
        // head2 is the last node of the original list 
        ListNode head2 = reverseList(mid.next);     // 2nd half LL is reversed
        
        mid.next = null;    // 1st LL k tail(mid) ko null kar do, Ab 2 alag alag LL bn chuke hai
        
        // Agar odd length ki list huyi toh reverse wali list choti hogi ek se, islye rev(tempHead2) ko null se check karwao
        
        boolean isPali = true;
        ListNode tempHead1 = head;
        ListNode tempHead2 = head2;
        // Iterate from start to end of both LL to check Palindrome
        while(tempHead2 != null && tempHead1 != null) {     // tempHead1 k wazah se loop kvi break nai hogi
            if(tempHead1.val != tempHead2.val) {
                isPali = false;
                break;
            }
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        } 

        // LL ko last me unmodify kar k return karna chahiye
        // Jo reverse kiya tha usko unreverse karo
        ListNode midNext = reverseList(head2);  // midNext is the original LL's midNext
        mid.next = midNext;                     // Original mid k next me midNext ko daal do
        
        // Checking LL is unmodified 
        // while(head != null) {
        //     System.out.println(head.val);
        //     head = head.next;
        // }
        
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
        if(head == null || head.next == null) return head;  
        
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

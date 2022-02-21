// https://leetcode.com/problems/reorder-list/

Intution :-
--------
Reverse kar do 2nd half ko
Ab, 2 separate LL bn gayi 
Now, iterate on both the LL and create links


class Solution {
    
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return; // Aadat
        
        ListNode mid = middleNode(head);             
        
        // 1st middle k next wale element se reverse karna shuru karo
        // head2 is the last node of the original list 
        ListNode head2 = reverseList(mid.next);     // 2nd half LL is reversed
        
        mid.next = null;    // 1st LL k tail(mid) ko null kar do, Ab 2 alag alag LL bn chuke hai
        
        // Agar odd length ki list huyi toh reverse wali list choti hogi ek se, islye rev(curr2) ko null se check karwao
        
        ListNode curr1 = head;
        ListNode curr2 = head2;
        // Iterate from start to end of both LL to form the links
        while(curr2 != null && curr1 != null) {     // curr1 k wazah se loop kvi break nai hogi
            ListNode forward1 = curr1.next;   // backup
            ListNode forward2 = curr2.next;   // backup
            
            curr1.next = curr2;     // link
            curr2.next = forward1;  //link
            
            curr1 = forward1;
            curr2 = forward2;
        }
        return;
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

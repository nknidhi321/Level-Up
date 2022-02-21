// https://leetcode.com/problems/odd-even-linked-list/

class Solution {
    
    public ListNode oddEvenList(ListNode head) {   
        if(head == null || head.next == null) return head;
        
        ListNode oddhead = head.next; // Keep hold of headsNext because it will manipulate in the loop
        
        ListNode ep = head;       // c1 = curr1 where ep = c1
        ListNode op = head.next;  // c2 = curr2 where op = c2
        while(op != null && op.next != null) { // [c2 != null : odd length] , [c2.next != null : even length]
            ListNode f = op.next; // backup forward node, where f = foward
            ep.next = f;          // link by skipping one node from curr 
            op.next = f.next;     // link by skipping one node from curr

            ep = f;               // move by skipping one node from curr
            op = f.next;          // move by skipping one node from curr
        }
        
        ep.next = oddhead;  // Saare evenIdx k baad saare oddIdx should come as per question
        return head;
    }
}
